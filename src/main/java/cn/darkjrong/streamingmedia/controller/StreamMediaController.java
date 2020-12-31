package cn.darkjrong.streamingmedia.controller;

import cn.darkjrong.streamingmedia.common.pojo.dto.PushFlowDTO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PushFlowVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.ResponseVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.StreamVO;
import cn.darkjrong.streamingmedia.service.StreamService;
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
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 *  流媒体管理 Controller
 * @author Rong.Jia
 * @date 2020/12/12 04:55
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/stream")
@Api(tags = "流媒体管理")
public class StreamMediaController {

    @Autowired
    private StreamService streamService;

    /**
     * 添加推流
     * @param pushFlowDTO 推流参数对象
     * @return 视频播放对象
     */
    @ApiOperation("添加推流")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PushFlowVO> pushFlow(@RequestBody PushFlowDTO pushFlowDTO) {

        log.info("pushFlow {}", pushFlowDTO.toString());

        PushFlowVO pushFlowVO = streamService.pushFlow(pushFlowDTO);

        return ResponseVO.success(pushFlowVO);
    }

    @ApiOperation("停止推流")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "推流ID", required = true, paramType = "path", dataTypeClass = String.class),
    })
    public ResponseVO stopPushFlow(@PathVariable("id") @NotBlank(message = "ID 不能为空") String id) {

        log.info("stopPushFlow {}", id);

        streamService.stopPushFlow(id);

        return ResponseVO.success();
    }

    @ApiOperation("查询所有视频流信息")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<List<StreamVO>> findAll() {

        log.info("findAll {}", System.currentTimeMillis());

        return ResponseVO.success(streamService.findAll());
    }


}
