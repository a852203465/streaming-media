package cn.darkjrong.streamingmedia.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 *  磁盘信息 VO对象
 * @author Rong.Jia
 * @date 2020/12/16 18:50
 */
@NoArgsConstructor
@Data
@ApiModel("磁盘信息 对照对象")
public class DiskVO implements Serializable {

    private static final long serialVersionUID = -6330821157373764456L;

    /**
     *  盘符
     */
    @ApiModelProperty("盘符")
    private String mountPoint;

    /**
     *  可用大小
     */
    @ApiModelProperty("可用大小")
    private Long available;

    /**
     *  总大小
     */
    @ApiModelProperty("总大小")
    private Long total;

    /**
     *  已使用
     */
    @ApiModelProperty("已使用")
    private Long usable;


}
