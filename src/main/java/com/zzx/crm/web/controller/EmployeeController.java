package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.EmployeeQueryObject;
import com.zzx.crm.service.EmployeeService;
import com.zzx.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zzx
 * @date 2021-01-25 15:53:56
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee_list")
    @ResponseBody
    public PageResult list(EmployeeQueryObject qo) {
        PageResult result = employeeService.queryForPage(qo);

        return result;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        Employee user = employeeService.getEmployeeForLogin(username, password);
        if (user != null) {
            session.setAttribute(UserContext.USER_IN_SESSION, user);
            result.put("success", true);
            result.put("msg", "登录成功");
        } else {
            result.put("success", false);
            result.put("msg", "用户名或密码有误");
        }
        return result;
    }
}
