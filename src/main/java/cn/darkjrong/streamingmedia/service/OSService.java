package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.vo.CpuInfoVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.DiskVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.GlobalMemoryVO;

import java.util.List;

/**
 * OS 信息业务层接口
 * @author Rong.Jia
 * @date 2020/12/14 22:18
 */
public interface OSService {

    /**
     *   获取系统CPU 系统使用率、用户使用率、利用率等等 相关信息
     * @return CPU 信息
     */
    CpuInfoVO getCpuInfo();

    /**
     * 获取内存相关信息，比如总内存、可用内存等
     * @return  内存相关信息
     */
    GlobalMemoryVO getMemory();

    /**
     * 获取磁盘相关信息，可能有多个磁盘（包括可移动磁盘等）
     * @return 磁盘相关信息
     */
    List<DiskVO> getDiskStores();






}
