package cn.darkjrong.streamingmedia.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 推流信息
 * @date 2020/12/29
 * @author Rong.Jia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stream implements Serializable {

    private static final long serialVersionUID = 3952331927005034663L;

    /**
     * 所属设备ID
     */
    private Long deviceId;

    /**
     * 0：停止推流，1：正在推流
     */
    private Byte onPublish;

    /**
     * 在线人数
     */
    private Integer onlineNumber;

    /**
     * 推流参数
     */
    private String params;

    /**
     * 推流命令
     */
    private String command;

    /**
     * hashCode(根据推流参数计算值)
     */
    private Integer hashCode;

    /**
     * 流ID
     */
    private String streamId;

    /**
     * 流媒体应用名称
     */
    private String app;

}