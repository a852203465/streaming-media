package cn.darkjrong.streamingmedia.controller;

import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceDTO;
import cn.darkjrong.streamingmedia.common.pojo.dto.DeviceFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.vo.DeviceVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.ResponseVO;
import cn.darkjrong.streamingmedia.common.validator.groupvlidator.DeviceGroupValidator;
import cn.darkjrong.streamingmedia.common.validator.groupvlidator.IdGroupValidator;
import cn.darkjrong.streamingmedia.common.validator.groupvlidator.PageGroupValidator;
import cn.darkjrong.streamingmedia.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotNull;

/**
 * 设备管理
 * @author Rong.Jia
 * @date 2020/12/21 20:16
 */
@Slf4j
@Api(tags = "设备管理")
@RestController
@Validated
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation("添加设备")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO saveDevice(@RequestBody @Validated(DeviceGroupValidator.class) DeviceDTO deviceDTO) {

        log.info("saveDevice {}", deviceDTO.toString());

        deviceService.saveDevice(deviceDTO);

        return ResponseVO.success();
    }

    @ApiOperation("修改设备")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO updateDevice(@RequestBody @Validated({DeviceGroupValidator.class, IdGroupValidator.class}) DeviceDTO deviceDTO) {

        log.info("updateDevice {}", deviceDTO.toString());

        deviceService.saveDevice(deviceDTO);

        return ResponseVO.success();
    }

    @ApiOperation("查询设备")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<DeviceVO>> findDevice(DeviceFilterDTO deviceFilterDTO) {

        log.info("findDevice {}", deviceFilterDTO.toString());

        Validation.buildDefaultValidatorFactory().getValidator().validate(deviceFilterDTO, PageGroupValidator.class);

        return ResponseVO.success(deviceService.findDevices(deviceFilterDTO));
    }

    @ApiOperation("删除设备")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备ID", required = true, paramType = "path", dataTypeClass = Long.class),
    })
    public ResponseVO deleteDevice(@PathVariable("id") @NotNull(message = "设备ID 不能为空") Long id) {

        log.info("deleteDevice {}", id);

        deviceService.deleteDevice(id);

        return ResponseVO.success();
    }


}
