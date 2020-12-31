package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.StreamingMediaApplicationTests;
import cn.darkjrong.streamingmedia.common.pojo.vo.CpuInfoVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.DiskVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.GlobalMemoryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * OS 接口测试类
 * @author Rong.Jia
 * @date 2020/12/14 22:19
 */
public class OSServiceTest extends StreamingMediaApplicationTests {

    @Autowired
    private OSService osService;

    @Test
    void getCpuInfoTest() {

        CpuInfoVO cpuInfoVO = osService.getCpuInfo();

        System.out.println(cpuInfoVO.toString());
    }

    @Test
    void getMemoryTest() {

        GlobalMemoryVO globalMemoryVO = osService.getMemory();

        System.out.println(globalMemoryVO.toString());

    }

    @Test
    void getDiskStores() {

        List<DiskVO> diskVOList = osService.getDiskStores();

        System.out.println(diskVOList);

    }



}
