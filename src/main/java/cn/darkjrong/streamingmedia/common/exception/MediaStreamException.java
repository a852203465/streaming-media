package cn.darkjrong.streamingmedia.common.exception;

import cn.darkjrong.streamingmedia.common.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *   项目自定义异常
 * @author Rong.Jia
 * @date 2019/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MediaStreamException extends RuntimeException  implements Serializable {

    private static final long serialVersionUID = -1501020198729282518L;

    /**
     *  异常code 码
     */
    private Integer code;

    /**
     * 异常详细信息
     */
    private String message;

    public MediaStreamException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public MediaStreamException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public MediaStreamException(ResponseEnum responseEnum, String message) {
        super(message);
        this.code = responseEnum.getCode();
        this.message = message;
    }

    public MediaStreamException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }

    public MediaStreamException(ResponseEnum responseEnum, Throwable t) {
        super(responseEnum.getMessage(), t);
        this.message = responseEnum.getMessage();
    }


}
