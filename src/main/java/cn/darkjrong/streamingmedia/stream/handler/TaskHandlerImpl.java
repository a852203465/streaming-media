package cn.darkjrong.streamingmedia.stream.handler;

import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;
import cn.darkjrong.streamingmedia.stream.manager.CommandManager;
import cn.darkjrong.streamingmedia.stream.utils.ExecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 任务处理实现
 *
 * @author eguid
 */
@Slf4j
@Service
public class TaskHandlerImpl implements TaskHandler {

    @Override
    public CommandTasker process(String id, String command) {
        CommandTasker tasker = null;
        try {
            tasker = ExecUtils.createTasker(id, command);

            log.info("执行命令行, id {}, command {}", id, command);

            return tasker;
        } catch (IOException e) {

            //运行失败，停止任务
            ExecUtils.stop(tasker);

            log.error("执行命令失败！进程和输出线程已停止, id {}, error {}", id, e.getMessage());

            // 出现异常说明开启失败，返回null
            return null;
        }
    }

    @Override
    public boolean stop(Process process) {
        return ExecUtils.stop(process);
    }

    @Override
    public boolean stop(Thread outHandler) {
        return ExecUtils.stop(outHandler);
    }

    @Override
    public boolean stop(Process process, Thread thread) {
        boolean ret = false;
        ret = stop(thread);
        ret = stop(process);
        return ret;
    }
}
