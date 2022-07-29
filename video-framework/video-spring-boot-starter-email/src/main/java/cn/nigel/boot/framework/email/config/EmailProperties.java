package cn.nigel.boot.framework.email.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("video.email")
@Data
public class EmailProperties {

    @NotEmpty(message = "email account can not be empty")
    private String account;

    @NotEmpty(message = "email password key can not be empty")
    private String password;

    @NotEmpty(message = "email host key can not be empty")
    private String host;

}
