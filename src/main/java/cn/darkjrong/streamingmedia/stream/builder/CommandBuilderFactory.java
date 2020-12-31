package cn.darkjrong.streamingmedia.stream.builder;

/**
 * 默认流式命令构建器工厂类
 * @author eguid
 *
 */
public class CommandBuilderFactory {

	public static CommandBuilder createBuilder() {
		return new DefaultCommandBuilder();
	};
	
	public static  CommandBuilder createBuilder(String rootPath) {
		return new DefaultCommandBuilder(rootPath);
	};
}
