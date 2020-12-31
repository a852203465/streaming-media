package cn.darkjrong.streamingmedia.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DVR DTO 对象
 * @author Rong.Jia
 * @date 2020/12/28 21:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("DVR 过滤查询 对照对象")
public class DvrFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -4952314310659718976L;

    /**
     * 设备ID
     */
    @ApiModelProperty("设备ID")
    private Long deviceId;



}
