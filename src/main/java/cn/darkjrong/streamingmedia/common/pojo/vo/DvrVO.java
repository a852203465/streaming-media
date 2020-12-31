package cn.darkjrong.streamingmedia.common.pojo.vo;

import cn.darkjrong.streamingmedia.common.pojo.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * DVR 信息
 * @author Rong.Jia
 * @date 2020/12/28 20:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("DVR 信息")
public class DvrVO extends BaseBean implements Serializable {

    private static final long serialVersionUID = 346120993765141184L;

    /**
     * 所属设备ID
     */
    @ApiModelProperty("所属设备ID")
    private Long deviceId;

    /**
     * 录像访问地址
     */
    @ApiModelProperty("录像访问地址")
    private String url;

    /**
     * 录像文件
     */
    @ApiModelProperty("录像文件")
    private String file;




















}

