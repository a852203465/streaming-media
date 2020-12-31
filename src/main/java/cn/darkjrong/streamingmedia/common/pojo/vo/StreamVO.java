package cn.darkjrong.streamingmedia.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *  流信息 VO对象
 * @author Rong.Jia
 * @date 2020/12/12 20:48
 */
@Data
@ApiModel("推流信息 对照对象")
public class StreamVO implements Serializable {

    private static final long serialVersionUID = -4239950534300933266L;

    /**
     * 所属设备ID
     */
    @ApiModelProperty("所属设备ID")
    private Long deviceId;

    /**
     * 是否在推流 0：停止推流，1：正在推流
     */
    @ApiModelProperty("是否在推流 0：停止推流，1：正在推流")
    private Byte onPublish;

    /**
     * 在线人数
     */
    @ApiModelProperty("在线人数")
    private Integer onlineNumber;

    /**
     * 流ID
     */
    @ApiModelProperty("流ID")
    private String streamId;

    /**
     * 流媒体应用名称
     */
    @ApiModelProperty("流媒体应用名称")
    private String app;


}
