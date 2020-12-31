package cn.darkjrong.streamingmedia.common.constants;

/**
 *  视频预览 URL常量类
 * @author Rong.Jia
 * @date 2020/12/12 05:39
 */
public class LivePreviewFormatConstant {

    /**
     * http://192.168.37.128:8080/live/tomcat9.flv
     * rtmp://192.168.37.128/live/tomcat9
     * http://192.168.37.128:8080/live/tomcat9.m3u8
     * rtsp://admin:12345@192.0.0.64:554
     */

    public static final String FLV_FORMAT = "http://%s:%d/%s/%s.flv";

    public static final String RTMP_FORMAT = "rtmp://%s/%s/%s";

    public static final String HLS_FORMAT = "http://%s:%d/%s/%s.m3u8";

    public static final String RTSP_FORMAT = "rtsp://%s:%s@%s:%s%s";


}
