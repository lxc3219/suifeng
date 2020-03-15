package org.suifeng.baseframework.api.result;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 方法体封装拦截器配置
 * @author luoxc
 */
public class ResultWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * 资源返回体封装
     * @author luoxc
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResultHandlerInterceptor()).addPathPatterns("/**");
    }

}
