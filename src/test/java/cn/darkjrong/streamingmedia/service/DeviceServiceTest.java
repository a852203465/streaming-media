package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.StreamingMediaApplicationTests;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceDTO;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Device;
import cn.darkjrong.streamingmedia.common.pojo.vo.DeviceVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 设备信息测试类
 * @author Rong.Jia
 * @date 2020/12/18 18:23
 */
class DeviceServiceTest extends StreamingMediaApplicationTests {

    @Autowired
    private DeviceService deviceService;

    @Test
    void findAll() {

        List<DeviceVO> deviceVOList = deviceService.findAll();

        System.out.println(deviceVOList);

    }

    @Test
    void saveDevice() {

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setIp("192.168.130.13");
        deviceDTO.setPassword("123456");
        deviceDTO.setSerialNumber("32132121");
        deviceDTO.setUsername("admin");
        deviceDTO.setPort("554");

        Long aLong = deviceService.saveDevice(deviceDTO);

        System.out.println(aLong);

    }

    @Test
    void findDevices() {

        DeviceFilterDTO deviceFilterDTO = new DeviceFilterDTO();
        deviceFilterDTO.setCurrentPage(1);
        deviceFilterDTO.setPageSize(20);

        PageVO<DeviceVO> pageVO = deviceService.findDevices(deviceFilterDTO);

        System.out.println(pageVO.toString());

    }

    @Test
    void updateDevice() {

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(104829881778442241L);
        deviceDTO.setIp("192.168.130.13");
        deviceDTO.setSerialNumber("123213211212321");
        deviceDTO.setUsername("admin1321");
        deviceDTO.setPassword("1412421");

        Long aLong = deviceService.updateDevice(deviceDTO);
        System.out.println(aLong);
    }



}