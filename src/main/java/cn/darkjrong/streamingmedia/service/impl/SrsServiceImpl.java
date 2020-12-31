package cn.darkjrong.streamingmedia.service.impl;

import cn.darkjrong.streamingmedia.common.config.UrlConfig;
import cn.darkjrong.streamingmedia.common.constants.NumberConstant;
import cn.darkjrong.streamingmedia.common.constants.UrlConstant;
import cn.darkjrong.streamingmedia.common.enums.UrlEnum;
import cn.darkjrong.streamingmedia.common.pojo.response.StreamResponse;
import cn.darkjrong.streamingmedia.common.utils.RestTemplateUtils;
import cn.darkjrong.streamingmedia.service.SrsService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  SRS 接口层
 * @author Rong.Jia
 * @date 2020/12/12 05:18
 */
@Slf4j
@Service
public class SrsServiceImpl implements SrsService {

    @Autowired
    private UrlConfig urlConfig;

    @Override
    public List<StreamResponse.StreamsBean> getStreams() {

        try {

            String url = urlConfig.gainWholeUrl(UrlConstant.SRS_IP, UrlConstant.SRS_PORT, UrlEnum.STREAM.getUrl());
            StreamResponse response = RestTemplateUtils.get(url, StreamResponse.class);

            if (ObjectUtil.isNotNull(response) && Validator.equal(NumberConstant.ZERO, response.getCode())) {
                return response.getStreams();
            }
        } catch (Exception e) {
            log.error("getStreams {}", e.getMessage());
        }

        return null;
    }

    @Override
    public StreamResponse.StreamsBean getStream(String name) {

        List<StreamResponse.StreamsBean> streamsBeanList = getStreams();

        if (ObjectUtil.isNotNull(streamsBeanList) && CollectionUtil.isNotEmpty(streamsBeanList)) {
            return streamsBeanList.stream().filter(streamsBean -> StrUtil.equals(name, streamsBean.getName())).findAny().orElse(null);
        }
        return null;
    }

}
