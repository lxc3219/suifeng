package org.suifeng.baseframework.api.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.suifeng.baseframework.api.service.SystemLogService;
import org.suifeng.baseframework.model.dto.AccessLogDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 访问日志拦截器
 */
@Component
@Slf4j
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    /**
     * 开始时间
     */
    private static final ThreadLocal<Date> START_TIME = new ThreadLocal<>();

    private SystemLogService systemAccessLogService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录当前时间
        START_TIME.set(new Date());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AccessLogDTO accessLog = new AccessLogDTO();
        try {
            // 初始化 accessLog
            initAccessLog(accessLog, request);
            // 执行插入 accessLog
            addAccessLog(accessLog);
            // TODO 提升：暂时不考虑 ELK 的方案。而是基于 MySQL 存储。如果访问日志比较多，需要定期归档。
        } catch (Throwable th) {
            log.error("[afterCompletion][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        } finally {
            clear();
        }
    }

    /**
     * 初识话访问日志信息
     * @param accessLog
     * @param request
     */
    private void initAccessLog(AccessLogDTO accessLog, HttpServletRequest request) {
        // TODO
    }

    /**
     * 异步入库
     * @param accessLog
     */
    @Async
    public void addAccessLog(AccessLogDTO accessLog) {
        try {
            systemAccessLogService.addAccessLog(accessLog);
        } catch (Throwable th) {
            log.error("[addAccessLog][插入访问日志({}) 发生异常({})", JSON.toJSONString(accessLog), ExceptionUtils.getRootCauseMessage(th));
        }
    }

    private static void clear() {
        START_TIME.remove();
    }

}
