package org.suifeng.baseframework.api.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.suifeng.baseframework.api.common.domain.CommonResult;
import org.suifeng.baseframework.api.result.constant.ResponseResultConstants;
import javax.servlet.http.HttpServletRequest;
import static org.suifeng.baseframework.api.result.RestHelper.ok;

/**
 * 返回体封装
 * @author luoxc
 */
@Slf4j
@Order(1)
@ControllerAdvice
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
     * @param returnType, converterType
     * @author luoxc
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = sra.getRequest();
        // 判断请求，是否有包装标记
        Object ann = request.getAttribute(ResponseResultConstants.RESPONSE_RESULT_ANN);
        return ann != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回体已经是CommonResult类型，则不必再次包装
        if ((body instanceof CommonResult)) {
            return body;
        }
        log.debug("进入 返回体 重写格式 处理中...");
        return ok(body);
    }

}
