package com.zzx.crm.web.controller;

import com.zzx.crm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zzx
 * @date 2021-01-25 15:53:56
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    public String index() {
        System.out.println("employee...");
        return "employee";
    }
}
