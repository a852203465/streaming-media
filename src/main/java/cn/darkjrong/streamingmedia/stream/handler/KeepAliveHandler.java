package cn.darkjrong.streamingmedia.stream.handler;

import cn.darkjrong.streamingmedia.stream.dao.TaskDao;
import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;
import cn.darkjrong.streamingmedia.stream.utils.ExecUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 任务保活处理器（一个后台保活线程，用于处理异常中断的持久任务）
 *
 * @author Rong.Jia
 */
@Slf4j
public class KeepAliveHandler extends Thread {

    /**
     * 待处理队列
     */
    private static Queue<String> queue = new ConcurrentLinkedQueue<>();

	/**
	 * 错误计数
	 */
	public int errIndex = 0;

	/**
	 * 安全停止线程标记
	 */
    public volatile int stopIndex = 0;

    private TaskDao taskDao;

    public KeepAliveHandler(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public static void add(String id) {
        if (queue != null) {
            queue.offer(id);
        }
    }

    public boolean stop(Process process) {
        if (process != null) {
            process.destroy();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        for (; stopIndex == 0; ) {
            if (queue == null) {
                continue;
            }
            String id = null;
            CommandTasker task = null;

            try {
                while (queue.peek() != null) {
                	log.error("准备重启任务 {}", queue);
                    id = queue.poll();
                    task = taskDao.get(id);
                    //重启任务
                    ExecUtils.restart(task);
                }
            } catch (IOException e) {
				log.error("任务重启失败 详情 id : {}, task : {}", id, task);
                //重启任务失败
                errIndex++;
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void interrupt() {
        stopIndex = 1;
    }

}
