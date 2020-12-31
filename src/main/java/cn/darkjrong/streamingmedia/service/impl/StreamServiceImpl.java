package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.config.UrlConfig;
import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.constants.ProtocolConstant;
import cn.darkjrong.streamingmedia.common.constants.LivePreviewFormatConstant;
import cn.darkjrong.streamingmedia.common.constants.UrlConstant;
import cn.darkjrong.streamingmedia.common.enums.ResponseEnum;
import cn.darkjrong.streamingmedia.common.exception.MediaStreamException;
import cn.darkjrong.streamingmedia.common.pojo.dto.PushFlowDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Stream;
import cn.darkjrong.streamingmedia.common.pojo.vo.PushFlowVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.StreamVO;
import cn.darkjrong.streamingmedia.service.DeviceService;
import cn.darkjrong.streamingmedia.service.SrsService;
import cn.darkjrong.streamingmedia.service.StreamService;
import cn.darkjrong.streamingmedia.service.base.impl.BaseServiceImpl;
import cn.darkjrong.streamingmedia.stream.builder.CommandBuilder;
import cn.darkjrong.streamingmedia.stream.builder.CommandBuilderFactory;
import cn.darkjrong.streamingmedia.stream.config.FFmpegConfig;
import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;
import cn.darkjrong.streamingmedia.stream.manager.CommandManager;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 流媒体 业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2020/12/12 04:20
 */
@Slf4j
@Service
public class StreamServiceImpl extends BaseServiceImpl<Stream, StreamVO> implements StreamService {

    /**
     *  推流队列, key: 流ID， value: 推流信息
     */
    private static final ConcurrentMap<String, Stream> PLUG_FLOW = new ConcurrentHashMap<>();

    @Autowired
    private CommandManager manager;

    @Autowired
    private FFmpegConfig fFmpegConfig;

    @Autowired
    private SrsService srsService;

    @Autowired
    private UrlConfig urlConfig;

    @Autowired
    private DeviceService deviceService;

    @Override
    public PushFlowVO pushFlow(PushFlowDTO pushFlowDTO) {

        String src = pushFlowDTO.getSrc();
        Long deviceId = pushFlowDTO.getDeviceId();

        if (ObjectUtil.isAllEmpty(src, deviceId)) {
            log.error("The source address or device ID cannot be null");
            throw new MediaStreamException(ResponseEnum.THE_SOURCE_ADDRESS_OR_DEVICE_ID_CANNOT_BE_NULL);
        }

        if (StrUtil.isBlank(src)) {
            src = deviceService.getRtspById(deviceId);
        }

        Integer hashCode = JSON.toJSONString(pushFlowDTO).hashCode();
        Stream stream = PLUG_FLOW.values().stream().filter(a -> Validator.equal(hashCode, a.getHashCode())).findAny().orElse(null);
        if (ObjectUtil.isNull(stream)) {
            String id64 = getId64();

            CommandBuilder builder = CommandBuilderFactory.createBuilder();
            builder.add("ffmpeg").add("-re");
            if (StrUtil.startWith(src, ProtocolConstant.RTSP_PROTOCOL)) {
                builder.add("-rtsp_transport", "tcp").add("-thread_queue_size", "102400");
            }

            builder.add("-i", src)
                    .add("-vcodec", pushFlowDTO.getVCodec())
                    .add("-acodec", pushFlowDTO.getACodec())
                    .add("-f", "flv").add("-flvflags", "no_duration_filesize")
                    .add("-y")
                    .add(ProtocolConstant.RTMP_PROTOCOL + urlConfig.gainSeparateIp(UrlConstant.SRS_IP) + StrUtil.SLASH + fFmpegConfig.getAppName() + StrUtil.SLASH + id64);

            String id = manager.start(id64, builder);

            if (StrUtil.isNotBlank(id)) {
                stream = saveStream(builder.get(), id, deviceId, pushFlowDTO, fFmpegConfig.getAppName());
            }
        }

        if (ObjectUtil.isNotNull(stream)) {
            return getPushFlowVO(stream.getStreamId(), fFmpegConfig.getAppName());
        }

        return null;
    }

    @Override
    public void stopPushFlow(String id) {

        CommandTasker commandTasker = manager.query(id);

        if (ObjectUtil.isNotNull(commandTasker)) {
            manager.stop(commandTasker.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOnPublishByStreamId(String streamId) {

        Stream stream = PLUG_FLOW.get(streamId);
        if (ObjectUtil.isNotNull(stream)) {
            stream.setOnPublish(NumberConstant.ONE.byteValue());
            PLUG_FLOW.put(streamId, stream);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOnlineNumberByStreamId(String streamId, Boolean flag) {
        Stream stream = PLUG_FLOW.get(streamId);
        if (ObjectUtil.isNotNull(stream)) {

            Integer streamOnlineNumber = stream.getOnlineNumber();
            if (flag) {
                int onlineNumber = streamOnlineNumber + NumberConstant.ONE;
                stream.setOnlineNumber(onlineNumber);
                PLUG_FLOW.put(streamId, stream);
            }else {
                int t = (streamOnlineNumber - NumberConstant.ONE);
                if (t <= NumberConstant.ZERO) {
                    this.deleteStreamByStreamId(streamId);
                }else {
                    stream.setOnlineNumber(t);
                    PLUG_FLOW.put(streamId, stream);
                }
            }
        }
    }

    @Override
    public void deleteStreamByStreamId(String streamId) {
        PLUG_FLOW.remove(streamId);
        this.stopPushFlow(streamId);
    }

    @Override
    public List<StreamVO> findAll() {

        return super.objectConversion(CollectionUtil.newArrayList(PLUG_FLOW.values()));
    }

    /**
     * 保存推流信息
     * @param command 推流命令
     * @param streamId 流ID
     * @param deviceId 设备ID
     * @param appName 流媒体应用名
     * @param params 推流参数
     * @return 推流信息
     */
    Stream saveStream(String command, String streamId, Long deviceId, Object params, String appName) {

        String param = JSON.toJSONString(params);

        // 推流参数+推流命令 hashCode
        Integer hashCode = param.hashCode();

        Stream stream = PLUG_FLOW.values().stream().filter(a -> StrUtil.equals(streamId, a.getStreamId())
                && Validator.equal(a.getHashCode(), hashCode)
                && Validator.equal(a.getApp(), appName)).findAny().orElse(null);

        if (ObjectUtil.isNull(stream)) {

            stream = new Stream();
            stream.setApp(appName);
            stream.setCommand(command);
            stream.setDeviceId(deviceId);
            stream.setOnlineNumber(NumberConstant.ZERO);
            stream.setOnPublish(NumberConstant.ZERO.byteValue());
            stream.setStreamId(streamId);
            stream.setHashCode(hashCode);
            stream.setParams(param);

            PLUG_FLOW.put(streamId, stream);
        }

        return stream;
    }

    /**
     * 获取播放链接信息
     * @param streamId 流ID
     * @param appName 流媒体应用名
     * @return 播放链接信息
     */
    private PushFlowVO getPushFlowVO(String streamId, String appName) {

        String ip = urlConfig.gainSeparateIp(UrlConstant.SERVER_IP);
        Integer port = urlConfig.gainSeparatePort(UrlConstant.SRS_STREAM_PORT);

        String hls = String.format(LivePreviewFormatConstant.HLS_FORMAT, ip, port, appName, streamId);
        String flv = String.format(LivePreviewFormatConstant.FLV_FORMAT, ip, port, appName, streamId);
        String rtmp = String.format(LivePreviewFormatConstant.RTMP_FORMAT, ip, appName, streamId);

        return new PushFlowVO(streamId, hls, flv, rtmp);
    }

    /**
     * 生成 流ID
     * @return 流ID
     * @date 2020/08/22 18:49
     * @author Rong.Jia
     */
    private String getId64() {
        return Base64.encode(IdUtil.randomUUID().getBytes());
    }

}
