package com.zzx.crm.service;

import com.zzx.crm.domain.Permission;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.PermissionQueryObject;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 13:53:58
 */
public interface PermissionService {
    int deleteByPrimaryKey(Long id);
    int insert(Permission record);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    int updateByPrimaryKey(Permission record);

    PageResult queryForPage(PermissionQueryObject qo);
}
