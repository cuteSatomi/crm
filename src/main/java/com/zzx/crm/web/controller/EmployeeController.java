package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.EmployeeQueryObject;
import com.zzx.crm.service.EmployeeService;
import com.zzx.crm.util.AjaxResult;
import com.zzx.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zzx
 * @date 2021-01-25 15:53:56
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee_save")
    @ResponseBody
    public AjaxResult save(Employee employee) {
        AjaxResult result = null;
        try {
            // 设置一些初始化的属性，默认密码：123
            employee.setPassword("123");
            // 默认不是超级管理员
            employee.setAdmin(false);
            // 默认在职
            employee.setState(true);
            employeeService.insert(employee);
            result = new AjaxResult("保存成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            result = new AjaxResult("保存失败，请联系管理员",false);
        }
        return result;
    }

    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject qo) {
        PageResult result = employeeService.queryForPage(qo);

        return result;
    }

    @RequestMapping("/employee")
    public String employee() {

        return "employee";
    }

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpSession session) {
        AjaxResult result = null;
        Employee user = employeeService.getEmployeeForLogin(username, password);
        if (user != null) {
            session.setAttribute(UserContext.USER_IN_SESSION, user);
            result = new AjaxResult("登陆成功", true);
        } else {
            result = new AjaxResult("登陆失败，用户名或密码有误", false);
        }
        return result;
    }
}
