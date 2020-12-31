package cn.darkjrong.streamingmedia.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 *  定时器连接池
 * @date 2019/6/11 20:45:22
 * @author Rong.Jia
 */
@Configuration
public class ScheduledConfig {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(50);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
