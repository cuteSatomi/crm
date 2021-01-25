package com.zzx.crm.service.impl;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zzx
 * @date 2021-01-25 14:12:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    public void testDeleteByPrimaryKey() {
    }

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employeeService.insert(employee);
    }

    public void testSelectByPrimaryKey() {
    }

    public void testSelectAll() {
    }

    public void testUpdateByPrimaryKey() {
    }
}