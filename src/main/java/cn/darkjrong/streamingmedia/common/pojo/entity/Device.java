package cn.darkjrong.streamingmedia.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 设备实体类
 * @date 2020/12/28
 * @author Rong.Jia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {

    private static final long serialVersionUID = -5720955764379654264L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 添加人
     */
    private String createdUser;

    /**
     * 添加时间
     */
    private Long createdTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 修改人
     */
    private String updatedUser;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * IP
     */
    private String ip;

    /**
     * 描述
     */
    private String description;

    /**
     * 端口（取流端口）
     */
    private String port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 设备名
     */
    private String name;

    /**
     * 是否在线（0：离线，1:在线）
     */
    private Byte online;

    /**
     * 主码流（多个时，以","）
     */
    private String mainStreams;

    /**
     * 子码流（多个时，以","）
     */
    private String subcodeFlows;

}