package cn.darkjrong.streamingmedia.common.pojo.vo;

import cn.darkjrong.streamingmedia.common.pojo.bean.BaseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  设备信息 VO对象
 * @date 2020/12/17
 * @author Rong.Jia
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设备信息 对照对象")
public class DeviceVO extends BaseBean implements Serializable {

    private static final long serialVersionUID = 3999783157189380296L;

    /**
     *  设备序列号
     */
    @ApiModelProperty("设备序列号")
    private String serialNumber;

    /**
     *  设备IP
     */
    @ApiModelProperty("设备IP")
    private String ip;

    /**
     *  设备端口（流端口）
     */
    @ApiModelProperty("设备端口")
    private String port;

    /**
     *  设备用户名
     */
    @ApiModelProperty("设备用户名")
    private String username;

    /**
     *  设备密码
     */
    @ApiModelProperty("设备密码")
    private String password;

    /**
     *  名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 是否在线（0：离线，1:在线）
     */
    @ApiModelProperty("是否在线（0：离线，1:在线）")
    private Byte online;

    /**
     * 主码流（多个时，以","）
     */
    @ApiModelProperty(value = "主码流（多个时，以\",\"）")
    private String mainStreams;

    /**
     * 子码流（多个时，以","）
     */
    @ApiModelProperty(value = "子码流（多个时，以\",\"）")
    private String subcodeFlows;




}