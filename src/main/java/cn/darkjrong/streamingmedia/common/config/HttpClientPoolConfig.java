package cn.darkjrong.streamingmedia.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ttpClient连接池
 *
 * @author Rong.Jia
 * @date 2019/12/28 21:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "http-client")
public class HttpClientPoolConfig {

    /**
     * 连接池的最大连接数
     */
    private int maxTotalConnect;

    /**
     * 同路由的并发数
     */
    private int maxConnectPerRoute;

    /**
     * 客户端和服务器建立连接超时，默认2s
     */
    private int connectTimeout = 2 * 1000;

    /**
     * 指客户端从服务器读取数据包的间隔超时时间,不是总读取时间，默认30s
     */
    private int readTimeout = 30 * 1000;

    /**
     *  字符集
     */
    private String charset = "UTF-8";

    /**
     * 重试次数,默认2次
     */
    private int retryTimes = 2;

    /**
     * 从连接池获取连接的超时时间,不宜过长,单位ms
     */
    private int connectionRequestTimout = 200;

    /**
     * 针对不同的地址,特别设置不同的长连接保持时间
     */
    private Map<String, Integer> keepAliveTargetHost;

    /**
     * 针对不同的地址,特别设置不同的长连接保持时间,单位 s
     */
    private int keepAliveTime = 60;


}
