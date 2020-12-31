package cn.darkjrong.streamingmedia.common.scheduling;

import cn.darkjrong.streamingmedia.service.DeviceService;
import cn.darkjrong.streamingmedia.service.SrsCallbackService;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  定时任务
 * @author Rong.Jia
 * @date 2020/12/21 21:09
 */
@Slf4j
@Component
@Profile("pro")
public class TimingTask {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SrsCallbackService srsCallbackService;

    /**
     *  设备监听
     */
    @Scheduled(fixedDelay = 5000)
    public void listeningOnline() {
        deviceService.listeningOnline();
    }

    /**
     *  SRS 停止队列监听
     */
    @Scheduled(fixedDelay = 21 * 1000)
    public void listeningQueue() {
        srsCallbackService.listeningQueue();
    }


}
