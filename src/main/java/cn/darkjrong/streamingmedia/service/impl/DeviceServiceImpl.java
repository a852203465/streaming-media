package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.constants.LivePreviewFormatConstant;
import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.enums.ResponseEnum;
import cn.darkjrong.streamingmedia.common.exception.MediaStreamException;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceDTO;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Device;
import cn.darkjrong.streamingmedia.common.pojo.query.DeviceQuery;
import cn.darkjrong.streamingmedia.common.pojo.vo.DeviceVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.common.utils.PageableUtils;
import cn.darkjrong.streamingmedia.common.utils.PropertyUtils;
import cn.darkjrong.streamingmedia.mapper.DeviceMapper;
import cn.darkjrong.streamingmedia.service.DeviceService;
import cn.darkjrong.streamingmedia.service.base.impl.BaseServiceImpl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 设备信息 业务层接口实现类
 * @author Rong.Jia
 * @date 2020/12/17 22:18
 */
@Slf4j
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device, DeviceVO> implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceVO> findAll() {

        List<Device> deviceList = deviceMapper.findAll();

        return super.objectConversion(deviceList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long saveDevice(DeviceDTO deviceDTO) {

        if (StrUtil.isBlank(deviceDTO.getSerialNumber())) {
            deviceDTO.setSerialNumber(IdUtil.fastSimpleUUID());
        }

        Device device = deviceMapper.findDeviceByIp(deviceDTO.getIp());
        if (ObjectUtil.isNotNull(device)) {
            log.error("Device already exists");
            throw new IllegalArgumentException(ResponseEnum.DEVICE_ALREADY_EXISTS.getMessage());
        }

        device = new Device();
        BeanUtil.copyProperties(deviceDTO, device);
        device.setCreatedTime(DateUtil.current(Boolean.FALSE));
        device.setOnline(NumberConstant.ZERO.byteValue());

        deviceMapper.insert(device);

        return device.getId();
    }

    @Override
    public PageVO<DeviceVO> findDevices(DeviceFilterDTO deviceFilterDTO) {

        if (deviceFilterDTO.getCurrentPage() < 0) {
            PageableUtils.basicPage(deviceFilterDTO.getCurrentPage(), deviceFilterDTO.getPageSize(),
                    deviceFilterDTO.getOrderType(), deviceFilterDTO.getOrderField());
        }

        DeviceQuery deviceQuery = BeanUtil.copyProperties(deviceFilterDTO, DeviceQuery.class);

        List<Device> deviceList = deviceMapper.findDevices(deviceQuery);
        PageInfo<Device> pageInfo = new PageInfo<Device>(deviceList);

        PageVO<DeviceVO> pageVO = new PageVO<>();
        PropertyUtils.copyProperties(pageInfo, pageVO, super.objectConversion(pageInfo.getList()));

        return pageVO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateDevice(DeviceDTO deviceDTO) {

        Device device = deviceMapper.selectByPrimaryKey(deviceDTO.getId());
        if (ObjectUtil.isNull(device)) {
            log.error("Device does not exist or has been deleted");
            throw new MediaStreamException(ResponseEnum.DEVICE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (!StrUtil.equals(device.getIp(), deviceDTO.getIp())) {
            if(ObjectUtil.isNotNull(deviceMapper.findDeviceByIp(deviceDTO.getIp()))) {
                log.error("Device already exists");
                throw new MediaStreamException(ResponseEnum.DEVICE_ALREADY_EXISTS);
            }
        }

        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(Boolean.TRUE);
        copyOptions.setIgnoreError(Boolean.TRUE);

        BeanUtil.copyProperties(deviceDTO, device, copyOptions);
        device.setUpdatedTime(DateUtil.current(Boolean.FALSE));

        deviceMapper.updateByPrimaryKey(device);

        return device.getId();
    }

    @Override
    public void listeningOnline() {

        List<Device> deviceList = deviceMapper.findAll();
        if (CollectionUtil.isNotEmpty(deviceList)) {
            for (Device device : deviceList) {
                Byte online = NetUtil.ping(device.getIp()) ? NumberConstant.ONE.byteValue()
                        : NumberConstant.ZERO.byteValue();

                if (!Validator.equal(online, device.getOnline())) {
                    this.updateOnlineById(device.getId(), online);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateOnlineById(Long id, Byte online) {

        if (ObjectUtil.isAllNotEmpty(id, online)) {
            deviceMapper.updateOnlineById(id, online);
        }

    }

    @Override
    public DeviceVO finOne(Long id) {
        if (Validator.isNotNull(id)) {
            return super.objectConversion(deviceMapper.selectByPrimaryKey(id));
        }
        return null;
    }

    @Override
    public String getRtspById(Long id) {

        DeviceVO deviceVO = this.finOne(id);
        if (ObjectUtil.isNull(deviceVO)) {
            log.error("getRtspById() Device does not exist or has been deleted");
            throw new MediaStreamException(ResponseEnum.DEVICE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (Validator.equal(deviceVO.getOnline(), NumberConstant.ZERO.byteValue())
                && Validator.isFalse(NetUtil.ping(deviceVO.getIp()))) {
            log.error("Device not online");
            throw new MediaStreamException(ResponseEnum.DEVICE_NOT_ONLINE);
        }

        // 主码流，子码流
        String mainStreams = deviceVO.getMainStreams();
        String subcodeFlows = deviceVO.getSubcodeFlows();

        // 可用码流
        String availableStreaming = StrUtil.EMPTY;

        if (StrUtil.isNotBlank(subcodeFlows)) {
            List<String> subcodeFlowList = StrUtil.split(subcodeFlows, CharUtil.COMMA);
            availableStreaming = subcodeFlowList.get(RandomUtil.randomInt(subcodeFlowList.size()));
        }else if (StrUtil.isNotBlank(mainStreams)) {
            List<String> mainStreamList = StrUtil.split(mainStreams, CharUtil.COMMA);
            availableStreaming = mainStreamList.get(RandomUtil.randomInt(mainStreamList.size()));
        }

        if (StrUtil.isNotBlank(availableStreaming) && !StrUtil.startWith(availableStreaming, StrUtil.SLASH)) {
            availableStreaming = StrUtil.SLASH + availableStreaming;
        }

        return String.format(LivePreviewFormatConstant.RTSP_FORMAT, deviceVO.getUsername(),
                deviceVO.getPassword(), deviceVO.getIp(), deviceVO.getPort(), availableStreaming);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteDevice(Long id) {

        Device device = deviceMapper.selectByPrimaryKey(id);
        if (ObjectUtil.isNull(device)) {
            log.error("deleteDevice() Device does not exist or has been deleted");
            throw new MediaStreamException(ResponseEnum.DEVICE_DOES_NOT_EXIST_OR_HAS_BEEN_DELETED);
        }

        if (Validator.equal(NumberConstant.ONE.byteValue(), device.getOnline())) {
            log.error("deleteDevice() The device is online and cannot be deleted");
            throw new MediaStreamException(ResponseEnum.THE_DEVICE_IS_ONLINE_AND_CANNOT_BE_DELETED);
        }

        deviceMapper.deleteByPrimaryKey(id);

    }
}
