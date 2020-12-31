package cn.darkjrong.streamingmedia.common.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *  设备信息过滤查询对象
 * @author Rong.Jia
 * @date 2020/12/18 18:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("设备信息过滤查询")
public class DeviceFilterDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -1963211691187605740L;

    /**
     * IP
     */
    @ApiModelProperty("IP")
    private String ip;

    /**
     *  名称 模糊查询
     */
    @ApiModelProperty("名称 模糊查询")
    private String name;

}
