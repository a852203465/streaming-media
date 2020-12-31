package cn.darkjrong.streamingmedia.stream.handler;

/**
 * 输出消息处理
 * @author Rong.Jia
 */
public interface OutHandlerMethod {

	/**
	 * 解析消息
	 * @param id-任务ID
	 * @param msg -消息
	 */
	void parse(String id, String msg);
	
	/**
	 * 任务是否异常中断
	 * @return
	 */
	boolean isbroken();



}
