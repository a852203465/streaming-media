package cn.darkjrong.streamingmedia.stream.entity;

import cn.darkjrong.streamingmedia.stream.handler.OutHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于存放任务id,任务主进程，任务输出线程
 * 
 * @author eguid
 * @since jdk1.7
 * @version 2016年10月29日
 */
@Getter
@Setter
@ToString
public class CommandTasker implements Serializable {

	private static final long serialVersionUID = 4482396563761854452L;

	/**
	 * 任务id
	 */
	private final String id;

	/**
	 * 命令行
	 */
	private final String command;

	/**
	 * 命令行运行主进程
	 */
	private Process process;

	/**
	 * 命令行消息输出子线程
	 */
	private OutHandler thread;

	public CommandTasker(String id, String command) {
		this.id = id;
		this.command=command;
	}

	public CommandTasker(String id, String command, Process process, OutHandler thread) {
		this.id = id;
		this.command=command;
		this.process = process;
		this.thread = thread;
	}


}
