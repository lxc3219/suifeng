package org.suifeng.baseframework.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ConfigurationProperties(prefix ="api.error.view.conf")
public class ErrorViewProperties {

    private String error = "error/index";

    private String forbidden = "error/403";

    private String notFound = "error/404";
}
