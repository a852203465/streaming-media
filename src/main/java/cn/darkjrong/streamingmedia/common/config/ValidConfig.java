package cn.darkjrong.streamingmedia.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *  字段验证配置 快速失败返回模式
 * @author Rong.Jia
 * @date 2019/9/20 15:37
 */
@Configuration
public class ValidConfig {

    @Bean
    public Validator validator() {

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
