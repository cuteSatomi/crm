package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Employee;
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
        return employeeMapper.insert(record);
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
}
