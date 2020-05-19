package org.suifeng.baseframework.api.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 异常界面
 * @author luoxc
 */
@Setter
@Getter
public class ExceptionPage {

    private String error = "error/index";

    private String forbidden = "error/403";

    private String notFound = "error/404";

}
