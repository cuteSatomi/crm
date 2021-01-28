package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.domain.Role;
import com.zzx.crm.mapper.EmployeeMapper;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.EmployeeQueryObject;
import com.zzx.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 15:25:51
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        int effectCount = employeeMapper.insert(record);
        // 需要维护员工与角色中间表的关系
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(record.getId(), role.getId());
        }
        return effectCount;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        // 执行更新之前需要删除旧的中间表关系并且建立新的中间表关系
        employeeMapper.deleteRelation(record.getId());
        for (Role role : record.getRoles()) {
            employeeMapper.insertRelation(record.getId(), role.getId());
        }
        return employeeMapper.updateByPrimaryKey(record);
    }

    @Override
    public Employee getEmployeeForLogin(String username, String password) {
        return employeeMapper.getEmployeeForLogin(username, password);
    }

    @Override
    public PageResult queryForPage(EmployeeQueryObject qo) {
        // 查询总记录数
        Long count = employeeMapper.queryForPageCount(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        // 查询结果集合
        List<Employee> result = employeeMapper.queryForPage(qo);

        return new PageResult(count.intValue(), result);
    }

    @Override
    public void updateStateByPrimaryKey(Long id) {
        employeeMapper.updateStateByPrimaryKey(id);
    }

    @Override
    public List<Long> queryByEid(Long eid) {
        return employeeMapper.queryByEid(eid);
    }
}
