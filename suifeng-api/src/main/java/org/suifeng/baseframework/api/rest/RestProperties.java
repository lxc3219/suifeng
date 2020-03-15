package org.suifeng.baseframework.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "api.rest")
public class RestProperties {

	/**
	 * 是否启用rest
	 */
	private Boolean enabled;

	/**
	 * 接口服务器地址
	 */
	private String serverAddr;

}
