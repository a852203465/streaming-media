package cn.darkjrong.streamingmedia.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  内存信息 VO 对象
 * @author Rong.Jia
 * @date 2020/12/14 22:37
 */
@Data
@ApiModel("内存信息 对照对象")
public class GlobalMemoryVO implements Serializable {

    private static final long serialVersionUID = -2436152918369090288L;

    /**
     * 可用内存
     */
    @ApiModelProperty("可用内存")
    private String available;

    /**
     *  总数
     */
    @ApiModelProperty("总数")
    private String total;

    /**
     *  虚拟内存 信息
     */
    @ApiModelProperty("虚拟内存 信息")
    private VirtualMemoryBean virtualMemory;

    /**
     *  物理内存 信息
     */
    @ApiModelProperty("物理内存 信息")
    private List<PhysicalMemoryBean> physicalMemory;

    @Data
    public static class VirtualMemoryBean {

        /**
         *  Swap 分区总大小
         */
        @ApiModelProperty("Swap 分区总大小")
        private String swapTotal;

        /**
         *  Swap 分区 已用大小
         */
        @ApiModelProperty("Swap 分区 已用大小")
        private String swapUsed;

        /**
         *  已用内存
         */
        @ApiModelProperty("已用内存")
        private String virtualInUse;

        /**
         *  最大内存
         */
        @ApiModelProperty("最大内存")
        private String virtualMax;
    }

    @Data
    public static class PhysicalMemoryBean {

        /**
         *  标签
         */
        @ApiModelProperty("标签")
        private String bankLabel;

        /**
         *  大小
         */
        @ApiModelProperty("大小")
        private long capacity;

        /**
         *  速率
         */
        @ApiModelProperty("速率")
        private int clockSpeed;

        /**
         *  厂商
         */
        @ApiModelProperty("厂商")
        private String manufacturer;

        /**
         *  内存类型
         */
        @ApiModelProperty("内存类型")
        private String memoryType;
    }



}
