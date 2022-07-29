package cn.nigel.boot.framework.tracer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * BizTracer配置类
 */
@ConfigurationProperties("video.tracer")
@Data
public class TracerProperties {
}
