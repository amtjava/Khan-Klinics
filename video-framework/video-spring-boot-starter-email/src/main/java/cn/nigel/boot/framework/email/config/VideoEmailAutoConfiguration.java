package cn.nigel.boot.framework.email.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class VideoEmailAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public EmailProperties emailProperties() {
        return new EmailProperties();
    }

    @Bean
    public SendEmailHandler defaultEmailHandler() {
        return new SendEmailHandler();
    }

}
