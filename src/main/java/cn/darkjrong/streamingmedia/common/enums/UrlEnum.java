package cn.darkjrong.streamingmedia.common.enums;

/**
 * 接口Url枚举类
 * @author Rong.Jia
 * @date 2020/09/27
 */
public enum UrlEnum {

    // 流
    STREAM("/api/v1/streams"),

    RAW("/api/v1/raw"),


    ;


    private String url;

    UrlEnum(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

}
