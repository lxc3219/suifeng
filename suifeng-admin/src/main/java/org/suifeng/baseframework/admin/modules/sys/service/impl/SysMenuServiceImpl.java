package org.suifeng.baseframework.admin.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suifeng.baseframework.admin.modules.sys.mapper.SysMenuMapper;
import org.suifeng.baseframework.admin.modules.sys.service.SysMenuService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luoxc
 * @since 1.0.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        // 读取 SysMenu 的权限标识数组
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        // 逐个，按照“逗号”分隔
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

}
