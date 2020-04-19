package org.suifeng.baseframework.admin.modules.sys.service;


import org.suifeng.baseframework.admin.modules.sys.entity.SysUser;

/**
 * 用户 业务层
 * 
 * @author ruoyi
 */
public interface SysUserService {


    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    public SysUser selectUserByUserName(String userName);

}
