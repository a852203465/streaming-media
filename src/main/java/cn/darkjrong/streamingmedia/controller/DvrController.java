package cn.darkjrong.streamingmedia.controller;

import cn.darkjrong.streamingmedia.common.pojo.dto.DvrFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.vo.DvrVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.ResponseVO;
import cn.darkjrong.streamingmedia.service.DvrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  DVR 管理
 * @author Rong.Jia
 * @date 2020/12/20 14:13
 */
@Slf4j
@Validated
@RequestMapping("/dvr")
@Api(tags = "DVR 管理")
@RestController
public class DvrController {

    @Autowired
    private DvrService dvrService;

    @ApiOperation("开启DVR")
    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流唯一标识", required = true, paramType = "path", dataTypeClass = String.class),
    })
    public ResponseVO<Boolean> enableDvr(@PathVariable("id") @NotBlank(message = "流唯一标识不为空") String id) {

        log.info("enableDvr {}", id);

        return ResponseVO.success(dvrService.enableDvr(id));
    }

    @ApiOperation("关闭DVR")
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流唯一标识", required = true, paramType = "path", dataTypeClass = String.class),
    })
    public ResponseVO<Boolean> disableDvr(@PathVariable("id") @NotBlank(message = "流唯一标识不为空") String id) {

        log.info("disableDvr {}", id);

        return ResponseVO.success(dvrService.disableDvr(id));
    }

    @ApiOperation("删除录像")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "录像ID", required = true, paramType = "path", dataTypeClass = Long.class),
    })
    public ResponseVO deleteVideo(@PathVariable("id") @NotNull(message = "录像ID不为空") Long id) {

        log.info("deleteVideo {}", id);

        dvrService.deleteVideo(id);

        return ResponseVO.success();
    }

    @ApiOperation("查询录像")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVO<PageVO<DvrVO>> findVideos(DvrFilterDTO dvrFilterDTO) {

        log.info("findVideos {}", dvrFilterDTO.toString());

        PageVO<DvrVO> pageVO = dvrService.findVideos(dvrFilterDTO);

        return ResponseVO.success(pageVO);
    }













}
