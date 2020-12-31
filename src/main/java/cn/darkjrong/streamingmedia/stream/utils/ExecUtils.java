package cn.darkjrong.streamingmedia.stream.utils;

import cn.darkjrong.streamingmedia.stream.entity.CommandTasker;
import cn.darkjrong.streamingmedia.stream.handler.OutHandler;
import cn.hutool.core.util.RuntimeUtil;

import java.io.IOException;

/**
 * 命令行操作工具类
 * @author Rong.Jia
 *
 */
public class ExecUtils {

	/**
	 * 执行命令行并获取进程
	 * @param cmd 命令
	 * @return Process  进程
	 * @throws IOException 执行异常
	 */
	public static Process exec(String cmd) throws IOException {

		Runtime runtime = Runtime.getRuntime();

		// 执行命令获取主进程
		return runtime.exec(cmd);
	}
	
	/**
	 * 销毁进程
	 * @param process 进程
	 * @return true: 成功，false：失败
	 */
	public static boolean stop(Process process) {
		if (process != null) {
			process.destroy();
			return true;
		}
		return false;
	}

	/**
	 * 销毁输出线程
	 * @param outHandler  输出线程
	 * @return  true: 成功，false：失败
	 */
	public static boolean stop(Thread outHandler) {
		if (outHandler != null && outHandler.isAlive()) {
			outHandler.stop();
			outHandler.destroy();
			return true;
		}
		return false;
	}
	
	/**
	 * 销毁任务
	 * @param tasker 任务
	 */
	public static void stop(CommandTasker tasker) {
		if(tasker!=null) {
			stop(tasker.getThread());
			stop(tasker.getProcess());
		}
	}

	/**
	 * 创建命令行任务
	 * @param id
	 * @param command 命令
	 * @return
	 * @throws IOException
	 */
	public static CommandTasker createTasker(String id,String command) throws IOException {

		// 执行本地命令获取任务主进程
		Process process=exec(command);

		// 创建输出线程
		OutHandler outHandler= OutHandler.create(process.getErrorStream(), id);

		return new CommandTasker(id, command, process, outHandler);
	}
	
	/**
	 * 中断故障缘故重启
	 * @param tasker
	 * @return
	 * @throws IOException
	 */
	public static CommandTasker restart(CommandTasker tasker) throws IOException {
		if(tasker!=null) {
			String id=tasker.getId(),command=tasker.getCommand();

			//安全销毁命令行进程和输出子线程
			stop(tasker);
			// 执行本地命令获取任务主进程
			Process process=exec(command);
			tasker.setProcess(process);
			// 创建输出线程
			OutHandler outHandler=OutHandler.create(process.getErrorStream(), id);
			tasker.setThread(outHandler);
		}
		return tasker;
	}
}
