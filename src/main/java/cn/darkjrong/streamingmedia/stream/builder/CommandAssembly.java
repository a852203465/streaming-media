package cn.darkjrong.streamingmedia.stream.builder;

import java.util.Map;

/**
 * 命令组装器接口
 *
 * @author eguid
 */
public interface CommandAssembly {

    /**
     * 将参数转为ffmpeg命令
     *
     * @param paramMap
     * @return
     */
    String assembly(Map<String, String> paramMap);

    String assembly();



}
