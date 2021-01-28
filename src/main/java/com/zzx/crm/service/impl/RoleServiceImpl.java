package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Permission;
import com.zzx.crm.domain.Role;
import com.zzx.crm.mapper.RoleMapper;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.RoleQueryObject;
import com.zzx.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 15:25:51
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        // 删除角色之前需要先删除中间表中的数据
        roleMapper.delOldRelationByRoleId(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role record) {
        // 先把角色插入role表
        int effectCount = roleMapper.insert(record);
        // 把角色拥有的权限插入role_permission，以此关联角色和权限
        for (Permission permission : record.getPermissions()) {
            roleMapper.insertRelation(record.getId(), permission.getId());
        }
        return effectCount;
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        int effectCount = roleMapper.updateByPrimaryKey(record);
        // 首先删除之前的角色权限对应关系
        roleMapper.delOldRelationByRoleId(record.getId());
        // 建立新的关系
        for (Permission permission : record.getPermissions()) {
            roleMapper.insertRelation(record.getId(), permission.getId());
        }
        return effectCount;
    }


    @Override
    public PageResult queryForPage(RoleQueryObject qo) {
        // 查询总记录数
        Long count = roleMapper.queryForPageCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        // 查询结果集合
        List<Role> result = roleMapper.queryForPage(qo);

        return new PageResult(count.intValue(), result);
    }
}
