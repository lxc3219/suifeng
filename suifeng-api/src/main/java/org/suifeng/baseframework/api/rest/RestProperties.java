package org.suifeng.baseframework.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "api.rest")
public class RestProperties {

	/**
	 * 启用开关，默认 false
	 */
	private boolean enabled;

	/**
	 * 接口服务器地址
	 */
	private String serverAddr;

	/**
	 * Http 请求配置
	 */
	private HttpRequestProperties http;

}
