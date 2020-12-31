package cn.darkjrong.streamingmedia.common.pojo.dto;

import cn.darkjrong.streamingmedia.common.pojo.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 *  设备信息 DTO 对象
 * @date 2020/12/17
 * @author Rong.Jia
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设备信息 参数对象")
public class DeviceDTO extends BaseBean implements Serializable {

    private static final long serialVersionUID = 3999783157189380296L;

    /**
     *  设备序列号
     */
    @ApiModelProperty("设备序列号")
    private String serialNumber;

    /**
     *  设备IP
     */
    @NotBlank(message = "设备IP 不能为空")
    @Pattern(regexp = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b", message = "设备IP 格式不正确")
    @ApiModelProperty(value = "设备IP", required = true)
    private String ip;

    /**
     *  设备端口（流端口）
     */
    @NotBlank(message = "设备端口（流端口） 不能为空")
    @ApiModelProperty(value = "设备端口（流端口）", required = true)
    private String port;

    /**
     *  设备用户名
     */
    @NotBlank(message = "设备用户名 不能为空")
    @ApiModelProperty(value = "设备用户名", required = true)
    private String username;

    /**
     *  设备密码
     */
    @NotBlank(message = "设备密码 不能为空")
    @ApiModelProperty(value = "设备密码", required = true)
    private String password;

    /**
     *  名称
     */
    @NotBlank(message = "名称 不能为空")
    @ApiModelProperty(value = "名称")
    private String name;

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