package cn.nigel.boot.framework.qiniu.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(QiniuProperties.class)
public class VideoQiniuAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public QiniuProperties qiniuProperties() {
        return new QiniuProperties();
    }

}
