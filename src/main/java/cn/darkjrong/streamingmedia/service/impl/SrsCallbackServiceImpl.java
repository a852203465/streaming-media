package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.pojo.dto.SrsCallbackDTO;
import cn.darkjrong.streamingmedia.service.DvrService;
import cn.darkjrong.streamingmedia.service.SrsCallbackService;
import cn.darkjrong.streamingmedia.service.StreamService;
import cn.darkjrong.streamingmedia.stream.config.FFmpegConfig;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * SRS 回调 业务层接口 实现类
 * @author Rong.Jia
 * @date 2020/12/13 23:53
 */
@Service
public class SrsCallbackServiceImpl implements SrsCallbackService {

    /**
     *  待停止推流队列, key: 流ID， value: 存入队列的时间
     */
    private static final ConcurrentMap<String, Long> STOP_QUEUE = new ConcurrentHashMap<>();

    /**
     * 调度执行器
     */
    private ScheduledExecutorService schedulerExecutor = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private StreamService streamService;

    @Autowired
    private FFmpegConfig fFmpegConfig;

    @Autowired
    private DvrService dvrService;

    @Override
    public void stop(SrsCallbackDTO callbackDTO) {
        STOP_QUEUE.put(callbackDTO.getStream(), DateUtil.current(Boolean.FALSE) / 1000);
        streamService.updateOnlineNumberByStreamId(callbackDTO.getStream(), Boolean.FALSE);
    }

    @Override
    public void play(SrsCallbackDTO callbackDTO) {
        STOP_QUEUE.remove(callbackDTO.getStream());
        streamService.updateOnlineNumberByStreamId(callbackDTO.getStream(), Boolean.TRUE);
    }

    @Override
    public void listeningQueue() {

        if (STOP_QUEUE.size() <= 0) return;

        Set<String> streamIds = STOP_QUEUE.keySet();
        for (Iterator<String> iterator = streamIds.iterator(); iterator.hasNext();) {

            String streamId = iterator.next();

            Long time = STOP_QUEUE.get(streamId);

            // 当前时间
            Long current = DateUtil.current(Boolean.FALSE) / 1000;

            if (current - time > NumberConstant.TWENTY.longValue()) {
                startRemovableScheduledFuture(streamId);
                STOP_QUEUE.remove(streamId);
            }
        }
    }

    @Override
    public void dvr(SrsCallbackDTO callbackDTO) {
        dvrService.saveVideo(callbackDTO.getFile(), callbackDTO.getStream());
    }

    @Override
    public void publish(SrsCallbackDTO callbackDTO) {
        streamService.updateOnPublishByStreamId(callbackDTO.getStream());
    }

    @Override
    public void unPublish(SrsCallbackDTO callbackDTO) {
        streamService.deleteStreamByStreamId(callbackDTO.getStream());
    }

    /**
     * 开启定时销毁推流
     * @param streamId 流ID
     */
    private void startRemovableScheduledFuture(String streamId) {
        schedulerExecutor.schedule(() -> streamService.stopPushFlow(streamId),
                fFmpegConfig.getDestroyDelay(), TimeUnit.SECONDS);
    }

}
