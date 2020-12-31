package cn.darkjrong.streamingmedia.common.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DVR 实体类
 * @date 2020/12/28
 * @author Rong.Jia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dvr implements Serializable {

    private static final long serialVersionUID = 4470130976091496795L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 所属设备ID
     */
    private Long deviceId;

    /**
     * 录像访问地址
     */
    private String url;

    /**
     * 录像文件
     */
    private String file;

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
     * 描述
     */
    private String description;

    /**
     * 原文件地址
     */
    private String rawFile;


}