package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Permission;
import com.zzx.crm.mapper.PermissionMapper;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.PermissionQueryObject;
import com.zzx.crm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 15:25:51
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }


    @Override
    public PageResult queryForPage(PermissionQueryObject qo) {
        // 查询总记录数
        Long count = permissionMapper.queryForPageCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        // 查询结果集合
        List<Permission> result = permissionMapper.queryForPage(qo);

        return new PageResult(count.intValue(), result);
    }
}
