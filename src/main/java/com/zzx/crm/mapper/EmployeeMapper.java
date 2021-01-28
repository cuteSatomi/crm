package com.zzx.crm.mapper;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Employee record);
    Employee selectByPrimaryKey(Long id);
    List<Employee> selectAll();
    int updateByPrimaryKey(Employee record);

    Employee getEmployeeForLogin(@Param("username") String username,@Param("password") String password);

    Long queryForPageCount(EmployeeQueryObject qo);

    List<Employee> queryForPage(EmployeeQueryObject qo);

    void updateStateByPrimaryKey(Long id);

    void insertRelation(@Param("eid")Long eid,@Param("rid")Long rid);

    List<Long> queryByEid(Long eid);

    void deleteRelation(Long eid);
}