package cn.darkjrong.streamingmedia.callback;

import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.pojo.dto.SrsCallbackDTO;
import cn.darkjrong.streamingmedia.service.SrsCallbackService;
import cn.darkjrong.streamingmedia.service.StreamService;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * SRS 回调接口
 * @author Rong.Jia
 * @date 2020/05/19 20:30
 */
@Slf4j
@RequestMapping("/api/v1/")
@RestController
public class SrsCallback {

    @Autowired
    private SrsCallbackService srsCallbackService;

    @PostMapping("connect")
    public Map<String, Integer> connect(@RequestBody String request) {
        return createMap();
    }

    @PostMapping("close")
    public Map<String, Integer> close(@RequestBody String request) {

        log.info("close {}", request);

        return createMap();
    }

    @PostMapping("publish")
    public Map<String, Integer> publish(@RequestBody String request) {

        log.info("publish {}", request);

        srsCallbackService.publish(JSONObject.parseObject(request, SrsCallbackDTO.class));

        return createMap();
    }

    @PostMapping("unpublish")
    public Map<String, Integer> unpublish(@RequestBody String request) {

        log.info("unpublish {}", request);

        srsCallbackService.unPublish(JSONObject.parseObject(request, SrsCallbackDTO.class));

        return createMap();
    }

    @PostMapping("play")
    public Map<String, Integer> play(@RequestBody String request) {

        log.info("play {}", request);

        srsCallbackService.play(JSONObject.parseObject(request, SrsCallbackDTO.class));

        return createMap();
    }

    @PostMapping("stop")
    public Map<String, Integer> stop(@RequestBody String request) {

        log.info("stop {}", request);

        srsCallbackService.stop(JSONObject.parseObject(request, SrsCallbackDTO.class));

        return createMap();
    }

    @PostMapping("dvr")
    public Map<String, Integer> dvr(@RequestBody String request) {

        log.info("dvr {}", request);

        srsCallbackService.dvr(JSONObject.parseObject(request, SrsCallbackDTO.class));

        return createMap();
    }

    private Map<String, Integer> createMap(){
        Map<String, Integer> result = CollectionUtil.newHashMap();
        result.put("code", NumberConstant.ZERO);
        return result;
    }







}
