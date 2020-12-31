package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.StreamingMediaApplicationTests;
import cn.darkjrong.streamingmedia.common.pojo.dto.PushFlowDTO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PushFlowVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  推流测试类
 * @author Rong.Jia
 * @date 2020/12/12 04:38
 */
class StreamServiceTest extends StreamingMediaApplicationTests {

    @Autowired
    private StreamService streamService;

    @Test
    void pushFlow() throws InterruptedException {

        PushFlowDTO pushFlowDTO = new PushFlowDTO();
        pushFlowDTO.setSrc("F:\\a.mp4");

        PushFlowVO pushFlowVO = streamService.pushFlow(pushFlowDTO);

        System.out.println(pushFlowVO.toString());

        Thread.sleep(300000000);

    }
}