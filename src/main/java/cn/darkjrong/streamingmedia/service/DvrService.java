package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.dto.DvrFilterDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Dvr;
import cn.darkjrong.streamingmedia.common.pojo.vo.DvrVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.PageVO;
import cn.darkjrong.streamingmedia.service.base.BaseService;

/**
 * DVR 业务层接口
 * @author Rong.Jia
 * @date 2020/12/27 23:21
 */
public interface DvrService extends BaseService<Dvr, DvrVO> {

    /**
     *  开启指定流视频
     * @param name 流的唯一名
     * @return true/false
     */
    boolean enableDvr(String name);

    /**
     *  关闭指定流视频
     * @param name 流的唯一名
     * @return true/false
     */
    boolean disableDvr(String name);

    /**
     * 保存录像信息
     * @param file 录像文件
     * @param streamId 流ID
     * @return 保存后的主键
     */
    Long saveVideo(String file, String streamId);

    /**
     * 过滤查询 DVR信息
     * @param dvrFilterDTO 过滤条件
     * @return  DVR信息
     */
    PageVO<DvrVO> findVideos(DvrFilterDTO dvrFilterDTO);

    /**
     * 删除 录像
     * @param id 主键ID
     */
    void deleteVideo(Long id);










}
