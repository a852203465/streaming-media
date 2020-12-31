package cn.darkjrong.streamingmedia.mapper;

import cn.darkjrong.streamingmedia.common.pojo.entity.Device;
import cn.darkjrong.streamingmedia.common.pojo.query.DeviceQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备持久层接口
 * @date 2020/12/28
 * @author Rong.Jia
 */
public interface DeviceMapper {

    /**
     * 根据主键 删除设备信息
     * @param id 主键
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0: 失败，1：成功
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增设备信息
     * @param device 设备信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0: 失败，1：成功
     */
    int insert(Device device);

    /**
     * 新增设备信息
     * @param device 设备信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0: 失败，1：成功
     */
    int insertSelective(Device device);

    /**
     * 根据主键 查询设备信息
     * @param id 主键
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 设备信息
     */
    Device selectByPrimaryKey(Long id);

    /**
     * 修改设备信息
     * @param device 设备信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0: 失败，1：成功
     */
    int updateByPrimaryKeySelective(Device device);

    /**
     * 修改设备信息
     * @param device 设备信息
     * @date 2020/12/28
     * @author Rong.Jia
     * @return 0: 失败，1：成功
     */
    int updateByPrimaryKey(Device device);

    /**
     * 查询所有设备信息
     * @return 设备信息集合
     */
    List<Device> findAll();

    /**
     * 根据IP 查询设备信息
     * @param ip  Ip
     * @return 设备信息
     */
    Device findDeviceByIp(@Param("ip") String ip);

    /**
     * 过滤查询设备信息
     * @param deviceQuery 过滤条件
     * @return 设备信息集合
     */
    List<Device> findDevices(DeviceQuery deviceQuery);

    /**
     * 根据主键 修改在线状态
     * @param id 主键ID
     * @param online 在线状态 （0：离线，1:在线）
     * @return 0：失败，1：成功
     */
    int updateOnlineById(@Param("id") Long id, @Param("online") Byte online);















}