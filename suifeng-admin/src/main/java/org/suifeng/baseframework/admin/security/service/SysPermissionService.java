package org.suifeng.baseframework.admin.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.suifeng.baseframework.admin.modules.sys.entity.SysUser;
import org.suifeng.baseframework.admin.modules.sys.service.SysMenuService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luoxc
 * @since 1.0.0
 */
public class SysPermissionService {

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            // 所有模块
            roles.add("*:*:*");
        } else {
            // 读取
            roles.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return roles;
    }

}
