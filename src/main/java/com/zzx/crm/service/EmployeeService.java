package com.zzx.crm.service;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.EmployeeQueryObject;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 13:53:58
 */
public interface EmployeeService {
    int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(String username, String password);

    PageResult queryForPage(EmployeeQueryObject qo);

    void updateStateByPrimaryKey(Long id);
}
