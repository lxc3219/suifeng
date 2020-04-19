package org.suifeng.baseframework.admin.modules.sys.mapper;

import java.util.List;

/**
 * @author luoxc
 * @since 1.0.0
 */
public interface SysMenuMapper {

    List<String> selectMenuPermsByUserId(Long userId);

}
