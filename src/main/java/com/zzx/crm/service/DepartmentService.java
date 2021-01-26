package com.zzx.crm.service;

import com.zzx.crm.domain.Department;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-26 20:43
 */
public interface DepartmentService {
    int deleteByPrimaryKey(Long id);
    int insert(Department record);
    Department selectByPrimaryKey(Long id);
    List<Department> selectAll();
    int updateByPrimaryKey(Department record);

    List<Department> queryForEmp();
}
