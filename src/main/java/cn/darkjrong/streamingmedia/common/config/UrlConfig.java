package cn.darkjrong.streamingmedia.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *  映射配置文件URL 实体类
 * @author Rong.Jia
 * @date 2020/09/18 16:16
 */
@ConfigurationProperties(prefix = "url")
@Component
public class UrlConfig {

    /**
     * ip
     */
    private Map<String, String> ip = new HashMap<>();

    /**
     * port
     */
    private Map<String, Integer> port = new HashMap<>();


    /**
     * 完整url
     */
    private String wholeUrl;

    /**
     * 主机地址
     */
    private String hostAddress;

    /**
     * 单独的ip
     */
    private String separateIp;

    /**
     * 单独的端口
     */
    private Integer separatePort;

    /**
     * 根据ip类型，端口类型 set完整的主机地址
     * @param ipType   ip类型
     * @param portType 端口类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public void setHostAddress(String ipType, String portType) {
        this.hostAddress = "http://" + this.getIp().get(ipType) + ":" + this.getPort().get(portType);
    }

    public String getHostAddress() {
        return hostAddress;
    }

    /**
     * 根据ip类型，端口类型 ,uri 后缀 set 完整的url
     * @param ipType    ip类型
     * @param portType  端口类型
     * @param urlSuffix url后缀
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public void setWholeUrl(String ipType, String portType, String urlSuffix) {
        this.wholeUrl = "http://" + this.getIp().get(ipType) + ":" + this.getPort().get(portType) + urlSuffix;
    }

    public String getWholeUrl() {
        return wholeUrl;
    }

    /**
     * 根据ip类型set单独的ip
     * @param ipType ip类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public void setSeparateIp(String ipType) {
        this.separateIp = this.getIp().get(ipType);
    }

    public String getSeparateIp() {
        return separateIp;
    }

    /**
     *  根据端口类型set单独的端口号
     * @param portType 端口类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public void setSeparatePort(String portType) {
        this.separatePort = this.getPort().get(portType);
    }

    public Integer getSeparatePort() {
        return separatePort;
    }

    public Map<String, String> getIp() {
        return ip;
    }

    public void setIp(Map<String, String> ip) {
        this.ip = ip;
    }

    public Map<String, Integer> getPort() {
        return port;
    }

    public void setPort(Map<String, Integer> port) {
        this.port = port;
    }

    /**
     * 根据ip类型，端口类型 获取完整的url
     * @param ipType   ip类型
     * @param portType 端口类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public String gainHostAddress(String ipType, String portType) {
        setHostAddress(ipType, portType);
        return getHostAddress();
    }

    /**
     * 根据ip类型，端口类型 ,uri 后缀获取完整的uri
     * @param ipType    ip类型
     * @param portType  端口类型
     * @param urlSuffix url后缀
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public String gainWholeUrl(String ipType, String portType, String urlSuffix) {
        setWholeUrl(ipType, portType, urlSuffix);
        return getWholeUrl();
    }

    /**
     * 根据端口号类型 获取端口号
     * @param portType 端口类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public Integer gainSeparatePort(String portType) {
        setSeparatePort(portType);
        return getSeparatePort();
    }

    /**
     * 根据ip类型 获取端ip
     * @param ipType ip类型
     * @date 2018/6/13 18:38:33
     * @author Rong.Jia
     */
    public String gainSeparateIp(String ipType) {
        setSeparateIp(ipType);
        return getSeparateIp();
    }

}
