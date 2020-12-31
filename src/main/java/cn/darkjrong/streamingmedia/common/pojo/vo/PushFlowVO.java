package cn.darkjrong.streamingmedia.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  视频流播放地址信息
 * @author Rong.Jia
 * @date 2020/12/12 04:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("视频流播放信息 对照对象")
public class PushFlowVO implements Serializable {

    private static final long serialVersionUID = -3237131536715479772L;

    /**
     *  流 唯一ID
     */
    @ApiModelProperty("流 唯一ID")
    private String id;

    /**
     *  HLS 播放地址
     */
    @ApiModelProperty("HLS 播放地址")
    private String hls;

    /**
     *  HTTP FLV 播放地址
     */
    @ApiModelProperty("HTTP FLV 播放地址")
    private String flv;

    /**
     *  RTMP 播放地址
     */
    @ApiModelProperty("RTMP 播放地址")
    private String rtmp;

}
