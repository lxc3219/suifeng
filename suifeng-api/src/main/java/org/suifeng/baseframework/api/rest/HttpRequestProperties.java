package org.suifeng.baseframework.api.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "api.rest.http")
public class HttpRequestProperties {

    /**
     * 连接池的最大连接数默认为0
     */
    private int maxConnTotal;

    /**
     * 单个主机的最大连接数
     */
    private int maxConnPerRoute;

    /**
     * 连接超时默认2s
     */
    private int connectTimeout;

    /**
     * 读取超时默认30s
     */
    private int readTimeout;

}
