package com.zzx.crm.mapper;

import com.zzx.crm.domain.Permission;
import com.zzx.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Permission record);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    int updateByPrimaryKey(Permission record);

    Long queryForPageCount(QueryObject qo);

    List<Permission> queryForPage(QueryObject qo);
}