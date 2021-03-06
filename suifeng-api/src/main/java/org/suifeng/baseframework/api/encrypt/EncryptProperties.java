package org.suifeng.baseframework.api.encrypt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.suifeng.baseframework.api.common.ApiPropertiesPrefix;

@ConfigurationProperties(prefix = ApiPropertiesPrefix.API_ENCRYPT_PROP_PREFIX)
public class EncryptProperties {
	/**
	 * AES加密KEY
	 */
	private String key;

	private String charset = "UTF-8";
	
	/**
	 * 开启调试模式，调试模式下不进行加解密操作，用于像Swagger这种在线API测试场景
	 */
	private boolean debug = false;
	
	/**
	 * 签名过期时间（分钟）
	 */
	private Long signExpireTime = 10L;
	
	public Long getSignExpireTime() {
		return signExpireTime;
	}

	public void setSignExpireTime(Long signExpireTime) {
		this.signExpireTime = signExpireTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
