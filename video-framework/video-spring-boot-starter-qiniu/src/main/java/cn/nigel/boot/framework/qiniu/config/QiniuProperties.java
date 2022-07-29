package cn.nigel.boot.framework.qiniu.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("video.qiniu")
@Data
public class QiniuProperties {

    @NotEmpty(message = "access key can not be empty")
    private String accessKey;

    @NotEmpty(message = "secret key can not be empty")
    private String secretKey;

    @NotEmpty(message = "bucket key can not be empty")
    private String bucket;

    @NotEmpty(message = "domain key can not be empty")
    private String domain;

}
