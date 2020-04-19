package org.suifeng.baseframework.admin.modules.sys.mapper;

import org.suifeng.baseframework.admin.modules.sys.entity.SysUser;

/**
 * @author luoxc
 * @since 1.0.0
 */
public interface SysUserMapper {

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

}
