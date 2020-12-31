package cn.darkjrong.streamingmedia.common.pojo.dto;

import cn.hutool.core.lang.Validator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  推流信息DTO对象
 * @author Rong.Jia
 * @date 2020/12/12 04:54
 */
@Data
@ApiModel("推流信息 参数对照对象")
public class PushFlowDTO implements Serializable {

    private static final long serialVersionUID = -8529290057086712572L;

    /**
     * 源地址
     */
    @ApiModelProperty(value = "源地址")
    private String src;

    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID，'src' 为空该字段有效")
    private Long deviceId;

    /**
     *  视频频推送格式, 默认：h264
     */
    @ApiModelProperty(value = "视频频推送格式, 默认：h264")
    private String vCodec = "h264";

    /**
     *  音频推送格式 , 默认：copy
     */
    @ApiModelProperty(value = "音频推送格式 , 默认：copy")
    private String aCodec = "copy";


}
