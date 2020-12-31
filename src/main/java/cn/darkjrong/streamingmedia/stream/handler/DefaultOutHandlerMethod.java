package cn.darkjrong.streamingmedia.stream.handler;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 默认任务消息输出处理
 * @author Rong.Jia
 */
@Slf4j
@Service
public class DefaultOutHandlerMethod implements OutHandlerMethod {

	/**
	 * 任务是否异常中断，如果
	 */
	public boolean isBroken=false;
	
	@Override
	public void parse(String id,String msg) {
		//过滤消息
		if (StrUtil.contains(msg, "fail")) {
			log.error("任务可能发生故障, id : {}, message : {}", id, msg);
			isBroken=true;
		}else if(StrUtil.contains(msg, "miss")) {
			log.error("任务可能发生丢包, id : {}, message : {}", id, msg);
			isBroken=true;
		}else {
			isBroken=false;
			log.info("消息, id : {}, message : {}", id, msg);
			if(!StrUtil.contains(msg, "frame")) {
//				log.info("消息, id : {}, message : {}", id, msg);
			}
		}
	}

	@Override
	public boolean isbroken() {
		return isBroken;
	}
	
}
