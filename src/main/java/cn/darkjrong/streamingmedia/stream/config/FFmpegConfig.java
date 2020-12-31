package cn.darkjrong.streamingmedia.stream.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  FFmpeg 配置文件
 * @author Rong.Jia
 * @date 2020/12/12 01:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "ffmpeg")
public class FFmpegConfig {

    private String path;

    private Boolean keepalive;

    private String appName;

    private Integer destroyDelay;

}
