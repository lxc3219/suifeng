package org.suifeng.baseframework.admin.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.suifeng.baseframework.admin.common.util.StringUtils;
import org.suifeng.baseframework.admin.modules.sys.entity.SysUser;
import org.suifeng.baseframework.admin.modules.sys.service.SysUserService;
import org.suifeng.baseframework.admin.security.LoginUser;
import org.suifeng.baseframework.model.base.BaseException;

/**
 * @author luoxc
 * @since 1.0.0
 */
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询指定用户名对应的 SysUser
        SysUser user = userService.selectUserByUserName(username);
        // 各种校验
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }

        // 创建 Spring Security UserDetails 用户明细
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }

}
