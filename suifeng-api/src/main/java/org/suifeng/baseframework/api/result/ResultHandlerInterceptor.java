package org.suifeng.baseframework.api.result;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.suifeng.baseframework.api.common.annotation.ResponseResult;
import org.suifeng.baseframework.api.common.annotation.RestResponseResult;
import org.suifeng.baseframework.api.result.constant.ResponseResultConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 根据方法或类是否有@ResponseResult、@RestResponseResult打标记，
 * 后续环节会根据该标记决定是否封装返回体
 * @author luoxc
 */
public class ResultHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            // TODO 优化，第一次请求解析，将是否需要包装加进缓存，以后均从缓存读取
            // 判断是否在类对象上面加了注解
            if (clazz.isAnnotationPresent(RestResponseResult.class) || clazz.isAnnotationPresent(ResponseResult.class)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                Object var = null;
                if (clazz.isAnnotationPresent(RestResponseResult.class)) {
                    var = clazz.getAnnotation(RestResponseResult.class);
                } else {
                    var = clazz.getAnnotation(ResponseResult.class);
                }
                request.setAttribute(ResponseResultConstants.RESPONSE_RESULT_ANN, var);
            }
            // 方法体上是否有注解
            else if (method.isAnnotationPresent(ResponseResult.class)) {
                // 设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(ResponseResultConstants.RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
