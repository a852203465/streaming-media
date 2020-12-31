package cn.darkjrong.streamingmedia.common.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 *  设备查询对象
 * @author Rong.Jia
 * @date 2020/12/18 18:46
 */
@Data
public class DeviceQuery implements Serializable {

    private static final long serialVersionUID = -853910581418880370L;

    /**
     * IP
     */
    private String ip;

    /**
     *  名称
     */
    private String name;


}
