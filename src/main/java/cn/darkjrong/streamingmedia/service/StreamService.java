package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.dto.PushFlowDTO;
import cn.darkjrong.streamingmedia.common.pojo.entity.Stream;
import cn.darkjrong.streamingmedia.common.pojo.vo.PushFlowVO;
import cn.darkjrong.streamingmedia.common.pojo.vo.StreamVO;
import cn.darkjrong.streamingmedia.service.base.BaseService;

import java.util.List;

/**
 *  流媒体 业务层接口
 * @author Rong.Jia
 * @date 2020/12/12 04:19
 */
public interface StreamService extends BaseService<Stream, StreamVO> {

    /**
     *  推流
     * @param pushFlowDTO 源地址信息
     * @return 取流 播放地址
     */
    PushFlowVO pushFlow(PushFlowDTO pushFlowDTO);

    /**
     *  停止推流
     * @param id 流唯一标识
     */
    void stopPushFlow(String id);

    /**
     * 根据流ID 修改 推流状态
     * @param streamId 流ID
     */
    void updateOnPublishByStreamId(String streamId);

    /**
     * 根据流ID 修改 在线人数
     * @param streamId 流ID
     * @param flag true: 开始播放，false: 停止播放
     */
    void updateOnlineNumberByStreamId(String streamId, Boolean flag);

    /**
     * 根据流ID 删除推流信息
     * @param streamId 流ID
     */
    void deleteStreamByStreamId(String streamId);

    /**
     * 查询推流信息
     * @return 推流信息
     */
    List<StreamVO> findAll();



}
