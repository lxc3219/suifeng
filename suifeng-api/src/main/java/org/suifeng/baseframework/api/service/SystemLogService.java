package org.suifeng.baseframework.api.service;

import org.suifeng.baseframework.model.dto.AccessLogDTO;

/**
 * 系统日志 Service 接口
 * @author luoxc
 * @since 1.0.0
 */
public interface SystemLogService {

    void addAccessLog(AccessLogDTO accessLogDTO);

}
