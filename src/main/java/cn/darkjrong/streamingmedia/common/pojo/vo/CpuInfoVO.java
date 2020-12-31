package cn.darkjrong.streamingmedia.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Cpu 信息 VO对象    系统CPU 系统使用率、用户使用率、利用率等等 相关信息
 * @author Rong.Jia
 * @date 2020/12/14 22:29
 */
@Data
@ApiModel("Cpu 信息 对照对象")
public class CpuInfoVO implements Serializable {

    private static final long serialVersionUID = -2333232600280875675L;

    /**
     * cpu核心数
     */
    @ApiModelProperty("cpu核心数")
    private Integer cpuNum;

    /**
     * CPU总的使用率
     */
    @ApiModelProperty("CPU总的使用率")
    private Double toTal;

    /**
     * CPU系统使用率
     */
    @ApiModelProperty("CPU系统使用率")
    private Double sys;

    /**
     * CPU用户使用率
     */
    @ApiModelProperty("CPU用户使用率")
    private Double used;

    /**
     * CPU当前等待率
     */
    @ApiModelProperty("CPU当前等待率")
    private Double wait;

    /**
     * CPU当前空闲率
     */
    @ApiModelProperty("CPU当前空闲率")
    private Double free;

    /**
     * CPU型号信息
     */
    @ApiModelProperty("CPU型号信息")
    private String cpuModel;



}
