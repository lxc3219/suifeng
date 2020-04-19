package org.suifeng.baseframework.admin.modules.sys.service;

import java.util.Set;

/**
 * @author luoxc
 * @since 1.0.0
 */
public interface SysMenuService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);
}
