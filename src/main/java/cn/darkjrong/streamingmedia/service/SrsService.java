package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.response.StreamResponse;

import java.util.List;

/**
 * SRS 流媒体 接口层
 *
 * @author Rong.Jia
 * @date 2020/05/15 18:31
 */
public interface SrsService {

    /**
     * 获取服务器的streams信息
     * @return streams信息
     */
    List<StreamResponse.StreamsBean> getStreams();

    /**
     *  是否有指定流信息
     * @param name 流唯一标识
     * @return 流信息, 不存在则返回空
     */
    StreamResponse.StreamsBean getStream(String name);









}
