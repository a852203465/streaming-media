package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.config.UrlConfig;
import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.constants.UrlConstant;
import cn.darkjrong.streamingmedia.common.enums.ResponseEnum;
import cn.darkjrong.streamingmedia.common.enums.SrsEnum;
import cn.darkjrong.streamingmedia.common.enums.UrlEnum;
import cn.darkjrong.streamingmedia.common.exception.MediaStreamException;
import cn.darkjrong.streamingmedia.common.pojo.dto.DvrDTO;
import cn.darkjrong.streamingmedia.common.pojo.dto.DvrFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Dvr;
import cn.darkjrong.streamingmedia.common.pojo.query.DvrQuery;
import cn.darkjrong.streamingmedia.common.pojo.response.StreamResponse;
import cn.darkjrong.streamingmedia.common.pojo.vo.DvrVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.common.utils.PageableUtils;
import cn.darkjrong.streamingmedia.common.utils.PropertyUtils;
import cn.darkjrong.streamingmedia.common.utils.RestTemplateUtils;
import cn.darkjrong.streamingmedia.mapper.DvrMapper;
import cn.darkjrong.streamingmedia.service.DvrService;
import cn.darkjrong.streamingmedia.service.SrsService;
import cn.darkjrong.streamingmedia.service.base.impl.BaseServiceImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * DVR 业务层接口实现类
 * @author Rong.Jia
 * @date 2020/12/27 23:21
 */
@Slf4j
@Service
public class DvrServiceImpl extends BaseServiceImpl<Dvr, DvrVO> implements DvrService {

    @Autowired
    private SrsService srsService;

    @Autowired
    private UrlConfig urlConfig;

    @Autowired
    private DvrMapper dvrMapper;

    @Override
    public boolean enableDvr(String name) {
        return dvr(name, SrsEnum.SwitchEnum.enable);
    }

    @Override
    public boolean disableDvr(String name) {
        return dvr(name, SrsEnum.SwitchEnum.disable);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveVideo(String file, String streamId) {

        Dvr dvr = new Dvr();
        dvr.setCreatedTime(DateUtil.current(Boolean.FALSE));

        // 根据流ID 获取所有设备
//        dvr.setDeviceId();

        dvr.setRawFile("/home/srs-3.0" + StrUtil.removePrefix(file, StrUtil.DOT));

        int lastIndex = file.lastIndexOf(StrUtil.SLASH);
        int lastSecondIndex = file.lastIndexOf(StrUtil.SLASH, lastIndex - 1);
        file = file.substring(lastSecondIndex);
        dvr.setFile(file);

        String host = urlConfig.gainHostAddress(UrlConstant.DVR_IP, UrlConstant.DVR_PORT);
        dvr.setUrl(host + file);

        dvrMapper.insert(dvr);

        return dvr.getId();
    }

    @Override
    public PageVO<DvrVO> findVideos(DvrFilterDTO dvrFilterDTO) {

        if (dvrFilterDTO.getCurrentPage() < 0) {
            PageableUtils.basicPage(dvrFilterDTO.getCurrentPage(), dvrFilterDTO.getPageSize(),
                    dvrFilterDTO.getOrderType(), dvrFilterDTO.getOrderField());
        }

        DvrQuery dvrQuery = BeanUtil.copyProperties(dvrFilterDTO, DvrQuery.class);

        List<Dvr> dvrList = dvrMapper.findDvrs(dvrQuery);
        PageInfo<Dvr> pageInfo = new PageInfo<Dvr>(dvrList);

        PageVO<DvrVO> pageVO = new PageVO<>();
        PropertyUtils.copyProperties(pageInfo, pageVO, super.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteVideo(Long id) {

        Dvr dvr = dvrMapper.selectByPrimaryKey(id);

        if (ObjectUtil.isNull(dvr)) {
            log.error("The video message does not exist or has been deleted");
            throw new MediaStreamException(ResponseEnum.THE_VIDEO_MESSAGE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        try {
            FileUtil.del(dvr.getRawFile());
        }catch (Exception ignored) {}

        dvrMapper.deleteByPrimaryKey(id);

    }

    /**
     * 开启、关闭录制视频
     * @param name 流唯一名
     * @param switchEnum 开启/关闭
     * @return 是否成功
     */
    private Boolean dvr(String name, SrsEnum.SwitchEnum switchEnum) {

        StreamResponse.StreamsBean streamsBean = srsService.getStream(name);
        if (ObjectUtil.isNull(streamsBean)) {
            log.error("dvr() Push flow information does not exist");
            throw new MediaStreamException(ResponseEnum.THE_PUSH_STREAM_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        DvrDTO dvrDTO = new DvrDTO();
        dvrDTO.setData(streamsBean.getApp() + StrUtil.SLASH + streamsBean.getName());
        dvrDTO.setParam(switchEnum.name());
        dvrDTO.setRpc(SrsEnum.RpcEnum.update.name());
        dvrDTO.setScope(SrsEnum.ScopeEnum.dvr.name());
        dvrDTO.setValue("__defaultVhost__");

        String url = urlConfig.gainWholeUrl(UrlConstant.SRS_IP, UrlConstant.SRS_PORT, UrlEnum.RAW.getUrl());

        try {
            String result = RestTemplateUtils.get(url, JSONObject.parseObject(JSON.toJSONString(dvrDTO), new TypeReference<Map<String, Object>>() {
            }), null, String.class);

            if (Validator.equal(NumberConstant.ZERO, JSONObject.parseObject(result).getInteger("code"))) {
                return Boolean.TRUE;
            }
        }catch (Exception e) {
            log.error("dvr {}", e.getMessage());
        }

        return Boolean.FALSE;
    }






}
