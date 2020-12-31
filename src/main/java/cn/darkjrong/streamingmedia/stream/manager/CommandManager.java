package cn.darkjrong.streamingmedia.stream.manager;

import cn.darkjrong.streamingmedia.stream.builder.CommandBuilder;
import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;

import java.util.Collection;
import java.util.Map;

/**
 * FFmpeg命令操作管理器，可执行FFmpeg命令/停止/查询任务信息
 *
 * @author eguid
 * @version 2016年10月29日
 * @since jdk1.7
 */
public interface CommandManager {

    /**
     * 通过命令发布任务（默认命令前不加FFmpeg路径）
     *
     * @param id      - 任务标识
     * @param command - FFmpeg命令
     * @return
     */
    String start(String id, String command);

    /**
     * 通过命令发布任务
     *
     * @param id      - 任务标识
     * @param commond - FFmpeg命令
     * @param hasPath - 命令中是否包含FFmpeg执行文件的绝对路径
     * @return
     */
    String start(String id, String commond, boolean hasPath);

    /**
     * 通过流式命令构建器发布任务
     *
     * @param commandBuilder
     * @return
     */
    String start(String id, CommandBuilder commandBuilder);

    /**
     * 通过组装命令发布任务
     *
     * @param assembly -组装命令（详细请参照readme文档说明）
     * @return
     */
    String start(Map<String, String> assembly);

    /**
     * 停止任务
     *
     * @param id
     * @return
     */
    boolean stop(String id);

    /**
     * 停止全部任务
     *
     * @return
     */
    int stopAll();

    /**
     * 通过id查询任务信息
     *
     * @param id
     */
    CommandTasker query(String id);

    /**
     * 查询全部任务信息
     */
    Collection<CommandTasker> queryAll();

    /**
     * 销毁一些后台资源和保活线程
     */
    void destory();

}
