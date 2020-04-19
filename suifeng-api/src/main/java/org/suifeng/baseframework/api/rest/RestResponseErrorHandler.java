package org.suifeng.baseframework.api.rest;

import org.suifeng.baseframework.api.common.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import java.io.IOException;

/**
 * 接口响应错误处理器，用于将接口返回的错误信息转换成 RestException
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {

	/**
	 * 重写handleError，抛出RestException
	 * @author luoxc
	 * @since 1.0.0
	 */
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = HttpStatus.resolve(response.getRawStatusCode());
		log.debug("rest call has error:" + statusCode.toString());
		String body = IOUtils.toString(response.getBody(), "UTF-8");
		throw new RestException(statusCode, body);
	}

}
