package org.suifeng.baseframework.api.service;

import org.suifeng.baseframework.model.dto.AccessLogDTO;

/**
 * 系统日志 Service 接口
 */
public interface SystemLogService {

    void addAccessLog(AccessLogDTO accessLogDTO);

}
