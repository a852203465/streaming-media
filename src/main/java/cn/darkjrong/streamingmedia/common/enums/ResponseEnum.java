package cn.darkjrong.streamingmedia.common.enums;

/**
 *  数据信息状态枚举类
 * @author Rong.Jia
 * @date 2019/4/2
 */
public enum ResponseEnum {

    /**
     *  枚举类code 开头使用规则：
     *
     *  全部正常，但不得不填充错误码时返回五个零：00000。
     *
     *  误码为字符串类型，共 5 位，分成两个部分：错误产生来源+四位数字编号。
     * 说明：错误产生来源分为 A/B/C，
     * A 表示错误来源于用户，比如参数错误，用户安装版本过低，用户支付超时等问题；
     * B 表示错误来源于当前系统，往往是业务逻辑出错，或程序健壮性差等问题；
     * C 表示错误来源于第三方服务，比如 CDN 服务出错，消息投递超时等问题；四位数字编号从 0001 到 9999，大类之间的步长间距预留 100，
     *
     */

    // 成功
    SUCCESS(0,"成功"),

    ERROR(-1, "失败"),
    SYSTEM_ERROR(500,"系统错误"),

    // 参数不正确
    PARAMETER_ERROR(400,"参数不正确"),

    // 未找到
    NOT_FOUND(404, "请求接口不存在"),

    // 请求方式错误
    REQUEST_MODE_ERROR(415,"请求方式错误, 请检查"),

    //媒体类型不支持
    MEDIA_TYPE_NOT_SUPPORTED(415,"媒体类型不支持"),

    // 1000：公共
    REQUEST_PARAMETER_FORMAT_IS_INCORRECT(400,"请求参数格式不正确"),
    THE_PARAMETER_TYPE_IS_INCORRECT(400, "参数类型不正确"),

    // 2000 流媒体
    PULL_FLOW_FAILURE(2001,"拉流失败, 请稍等片刻再试"),
    THE_PUSH_STREAM_INFORMATION_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(2002,"推流信息不存在, 或已删除"),
    THE_SOURCE_ADDRESS_OR_DEVICE_ID_CANNOT_BE_NULL(2003, "源地址或者设备ID不能为空"),


    // 3000 设备
    DEVICE_ALREADY_EXISTS(3001, "设备已存在"),
    DEVICE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(3002, "设备不存在或者已删除"),
    DEVICE_NOT_ONLINE(3003, "设备不在线"),
    THE_DEVICE_IS_ONLINE_AND_CANNOT_BE_DELETED(3004,"设备在线不可删除"),


    // 录像
    THE_VIDEO_MESSAGE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED(4001, "录像信息不存在或已删除"),








    ;

    private Integer code;

    private String message;

    ResponseEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
