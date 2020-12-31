package cn.darkjrong.streamingmedia;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  Spring Boot 启动类
 * @author Rong.Jia
 * @date 2020/12/12 00:49
 */
@EnableScheduling
@EnableAsync
@EnableSwagger2Doc
@EnableTransactionManagement
@MapperScan("cn.darkjrong.streamingmedia.mapper")
@SpringBootApplication(scanBasePackages = {"cn.darkjrong.*", "cn.hutool.*"})
public class StreamingMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingMediaApplication.class, args);
	}

}
