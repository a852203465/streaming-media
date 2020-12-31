package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceDTO;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Device;
import cn.darkjrong.streamingmedia.common.pojo.vo.DeviceVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.service.base.BaseService;

import java.util.List;

/**
 * 设备信息 业务层接口
 * @author Rong.Jia
 * @date 2020/12/17 21:08
 */
public interface DeviceService extends BaseService<Device, DeviceVO> {

    /**
     *  查询所有设备
     * @return 设备信息
     */
    List<DeviceVO> findAll();

    /**
     *  添加设备信息
     * @param deviceDTO 设备信息
     * @return 成功后的设备主键
     */
    Long saveDevice(DeviceDTO deviceDTO);

    /**
     *  过滤查询设备信息
     * @param deviceFilterDTO 过滤条件
     * @return  设备信息
     */
    PageVO<DeviceVO> findDevices(DeviceFilterDTO deviceFilterDTO);

    /**
     * 修改设备信息
     * @param deviceDTO 设备信息
     * @return 成功后的设备主键
     */
    Long updateDevice(DeviceDTO deviceDTO);

    /**
     *  设备在线离线监听
     */
    void listeningOnline();

    /**
     * 根据主键修改 在线状态
     * @param id 主键
     * @param online  是否在线（0：离线，1:在线）
     */
    void updateOnlineById(Long id, Byte online);

    /**
     *  根据设备ID 查询设备信息
     * @param id 设备ID
     * @return 设备信息
     */
    DeviceVO finOne(Long id);

    /**
     * 根据设备ID 获取RTSP 地址
     * @param id 设备ID
     * @return RTSP 地址
     */
    String getRtspById(Long id);

    /**
     * 根据主键ID 删除设备信息
     * @param id 主键ID
     */
    void deleteDevice(Long id);





}
