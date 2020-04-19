package org.suifeng.baseframework.api.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.suifeng.baseframework.api.result.annotation.ResponseResult;
import org.suifeng.baseframework.api.result.annotation.RestResponseResult;
import org.suifeng.baseframework.model.vo.CommonResult;
import static org.suifeng.baseframework.api.common.helper.RestHelper.ok;

/**
 * 返回体封装
 * @author luoxc
 * @since 1.0.0
 */
@Order(1)
@ControllerAdvice
@EnableConfigurationProperties(ResponseResultProperties.class)
public class ResultResponseBodyAdvice implements ResponseBodyAdvice {

    @Autowired
    private ResponseResultProperties responseResultProperties;

    /**
     * 这个方法表示对于哪些请求要执行 beforeBodyWrite，返回 true 执行，返回 false 不执行
     * @param  returnType, converterType
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 根据配置是否使用 @RestResponseResult 或 @ResponseResult 启用返回体包装
        if (responseResultProperties.isAnn()) {
            Class clazz = returnType.getContainingClass();
            return clazz.isAnnotationPresent(RestResponseResult.class) ||
                    clazz.isAnnotationPresent(ResponseResult.class) ||
                    returnType.hasMethodAnnotation(ResponseResult.class);
        }
        return true;
    }

    /**
     * 重写返回体
     * @param  body, returnType, selectedContentType, selectedConverterType, request, response
     * @return java.lang.Object
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回体已经是 CommonResult 类型，则不必再次包装
        if ((body instanceof CommonResult)) {
            return body;
        }
        return ok(body);
    }

}
