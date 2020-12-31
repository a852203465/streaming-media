package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.pojo.vo.CpuInfoVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.DiskVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.GlobalMemoryVO;
import cn.darkjrong.streamingmedia.service.OSService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.util.List;

/**
 * OS 信息业务层接口实现类
 *
 * @author Rong.Jia
 * @date 2020/12/14 22:19
 */
@Slf4j
@Service
public class OSServiceImpl implements OSService {

    @Override
    public CpuInfoVO getCpuInfo() {

        CpuInfo cpuInfo = OshiUtil.getCpuInfo();

        return BeanUtil.copyProperties(cpuInfo, CpuInfoVO.class);
    }

    @Override
    public GlobalMemoryVO getMemory() {

        GlobalMemory memory = OshiUtil.getMemory();

        return JSONObject.parseObject(JSON.toJSONString(memory), GlobalMemoryVO.class);
    }

    @Override
    public List<DiskVO> getDiskStores() {

        List<DiskVO> diskVOList = CollectionUtil.newArrayList();

        File[] disks = File.listRoots();
        for (File file : disks) {
            DiskVO diskVO = new DiskVO();
            diskVO.setMountPoint(file.getPath());
            diskVO.setTotal( file.getTotalSpace());
            diskVO.setAvailable(file.getFreeSpace());
            diskVO.setUsable( file.getUsableSpace());

            diskVOList.add(diskVO);
        }

        return diskVOList;
    }

}
