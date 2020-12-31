package cn.darkjrong.streamingmedia.stream.builder;

import cn.darkjrong.streamingmedia.stream.config.FFmpegConfig;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 默认流式命令行构建器（非线程安全）
 *
 * @author eguid
 */
public class DefaultCommandBuilder implements CommandBuilder {

    private FFmpegConfig fFmpegConfig = SpringUtil.getBean(FFmpegConfig.class);

    private StringBuilder builder = null;
    private String command = null;

    public DefaultCommandBuilder() {
        create();
    }

    public DefaultCommandBuilder(String rootPath) {
        create(rootPath);
    }

    @Override
    public CommandBuilder create(String rootPath) {
        builder = new StringBuilder(rootPath);
        return this;
    }

    @Override
    public CommandBuilder create() {
        return create(fFmpegConfig.getPath());
    }

    @Override
    public CommandBuilder add(String key, String val) {
        return add(key).add(val);
    }

    @Override
    public CommandBuilder add(String val) {
        if (builder != null) {
			builder.append(val);
			addBlankSpace();
        }
        return this;
    }

    @Override
    public CommandBuilder build() {
        if (builder != null) {
            command = builder.toString();
        }
        return this;
    }

    private void addBlankSpace() {
		builder.append(" ");
    }

    @Override
    public String get() {
        if (command == null) {
            build();
        }
        return command;
    }

}
