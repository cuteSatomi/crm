package com.zzx.crm.service;

import com.zzx.crm.domain.Employee;

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
}
