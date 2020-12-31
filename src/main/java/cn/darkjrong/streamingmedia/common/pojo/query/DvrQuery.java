package cn.darkjrong.streamingmedia.common.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * DVR 查询对象
 * @author Rong.Jia
 * @date 2020/12/28 20:54
 */
@Data
public class DvrQuery implements Serializable {

    private static final long serialVersionUID = -6459392867881602385L;

    /**
     * 设备ID
     */
    private Long deviceId;

}
