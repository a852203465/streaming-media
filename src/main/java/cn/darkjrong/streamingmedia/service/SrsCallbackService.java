package cn.darkjrong.streamingmedia.service;

import cn.darkjrong.streamingmedia.common.pojo.dto.SrsCallbackDTO;

/**
 * SRS 回调 业务层接口
 * @author Rong.Jia
 * @date 2020/12/13 23:46
 */
public interface SrsCallbackService {

    /**
     *  停止回调
     * @param callbackDTO  回调 信息
     */
    void stop(SrsCallbackDTO callbackDTO);

    /**
     *  开始播放回调
     * @param callbackDTO  回调 信息
     */
    void play(SrsCallbackDTO callbackDTO);

    /**
     *  监听队列
     */
    void listeningQueue();

    /**
     *  录像
     * @param callbackDTO  回调 信息
     */
    void dvr(SrsCallbackDTO callbackDTO);

    /**
     *  发布流回调
     * @param callbackDTO  回调 信息
     */
    void publish(SrsCallbackDTO callbackDTO);

    /**
     *  停止发布流回调
     * @param callbackDTO  回调 信息
     */
    void unPublish(SrsCallbackDTO callbackDTO);

}
