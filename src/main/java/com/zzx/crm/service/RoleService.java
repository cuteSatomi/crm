package com.zzx.crm.service;

import com.zzx.crm.domain.Role;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.RoleQueryObject;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 13:53:58
 */
public interface RoleService {
    int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);

    PageResult queryForPage(RoleQueryObject qo);
}
