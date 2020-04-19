package org.suifeng.baseframework.api.rest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import java.io.IOException;

/**
 * 请求拦截器，自定义消息头
 * @author luoxc
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

	private String headerName;

	private String headerValue;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpRequest wrapper = new HttpRequestWrapper(request);
		wrapper.getHeaders().set(headerName, headerValue);
		return execution.execute(wrapper, body);
	}
}