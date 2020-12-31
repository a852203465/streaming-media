package cn.darkjrong.streamingmedia.stream.manager.impl;

import cn.darkjrong.streamingmedia.stream.builder.CommandAssembly;
import cn.darkjrong.streamingmedia.stream.builder.CommandBuilder;
import cn.darkjrong.streamingmedia.stream.config.FFmpegConfig;
import cn.darkjrong.streamingmedia.stream.dao.TaskDao;
import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;
import cn.darkjrong.streamingmedia.stream.handler.KeepAliveHandler;
import cn.darkjrong.streamingmedia.stream.handler.OutHandlerMethod;
import cn.darkjrong.streamingmedia.stream.handler.TaskHandler;
import cn.darkjrong.streamingmedia.stream.manager.CommandManager;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * FFmpeg命令操作管理器
 *
 * @author eguid
 */
@Slf4j
@Service
@DependsOn("springUtil")
public class CommandManagerImpl implements CommandManager {

	@Autowired
    private TaskDao taskDao;

	@Autowired
    private TaskHandler taskHandler;

    @Autowired
    private CommandAssembly commandAssembly;

    private KeepAliveHandler keepAliveHandler;

    private FFmpegConfig fFmpegConfig;

    public CommandManagerImpl() {

        this.fFmpegConfig = SpringUtil.getBean(FFmpegConfig.class);

        if (fFmpegConfig.getKeepalive()) {
            keepAliveHandler = new KeepAliveHandler(taskDao);
            keepAliveHandler.start();
        }

    }

    @Override
    public String start(String id, String command) {
        return start(id, command, false);
    }

    @Override
    public String start(String id, String command, boolean hasPath) {

        if (id != null && command != null) {
            CommandTasker tasker = taskHandler.process(id, hasPath ? command : fFmpegConfig.getPath() + command);
            if (tasker != null) {
                int ret = taskDao.add(tasker);
                if (ret > 0) {
                    return tasker.getId();
                } else {

                    log.error("持久化失败，停止任务");

                    // 持久化信息失败，停止处理
                    taskHandler.stop(tasker.getProcess(), tasker.getThread());
                }
            }
        }
        return null;
    }

    @Override
    public String start(Map<String, String> assembly) {

        // 参数是否符合要求
        if (assembly == null || assembly.isEmpty() || !assembly.containsKey("appName")) {
            log.error("参数不正确，无法执行");
            return null;
        }
        String appName = (String) assembly.get("appName");
        if (appName != null && "".equals(appName.trim())) {
            log.error("appName不能为空");
            return null;
        }
        assembly.put("ffmpegPath", fFmpegConfig.getPath() + "ffmpeg");

        String command = commandAssembly.assembly(assembly);
        if (command != null) {
            return start(appName, command, true);
        }

        return null;
    }

    @Override
    public String start(String id, CommandBuilder commandBuilder) {

        String command = commandBuilder.get();
        if (command != null) {
            return start(id, command, true);
        }
        return null;
    }

    @Override
    public boolean stop(String id) {

        if (id != null && taskDao.isHave(id)) {

            log.info("停止任务 {}", id);

            CommandTasker tasker = taskDao.get(id);
            if (taskHandler.stop(tasker.getProcess(), tasker.getThread())) {
                taskDao.remove(id);
                return true;
            }
        }

        log.error("停止任务失败 id :{}", id);

        return false;
    }

    @Override
    public int stopAll() {
        Collection<CommandTasker> list = taskDao.getAll();
        Iterator<CommandTasker> iter = list.iterator();
        CommandTasker tasker = null;
        int index = 0;
        while (iter.hasNext()) {
            tasker = iter.next();
            if (taskHandler.stop(tasker.getProcess(), tasker.getThread())) {
                taskDao.remove(tasker.getId());
                index++;
            }
        }
        return index;
    }

    @Override
    public CommandTasker query(String id) {
        return taskDao.get(id);
    }

    @Override
    public Collection<CommandTasker> queryAll() {
        return taskDao.getAll();
    }

    @Override
    public void destory() {
        if (keepAliveHandler != null) {
            //安全停止保活线程
            keepAliveHandler.interrupt();
        }
    }
}
