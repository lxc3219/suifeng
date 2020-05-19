package org.suifeng.baseframework.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.suifeng.baseframework.api.common.ApiPropertiesPrefix;

@Setter
@Getter
@ConfigurationProperties(prefix = ApiPropertiesPrefix.API_REST_PROP_PREFIX)
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
