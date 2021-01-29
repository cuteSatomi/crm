package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.domain.Menu;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.EmployeeQueryObject;
import com.zzx.crm.service.EmployeeService;
import com.zzx.crm.service.MenuService;
import com.zzx.crm.service.PermissionService;
import com.zzx.crm.util.AjaxResult;
import com.zzx.crm.util.PermissionUtil;
import com.zzx.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 15:53:56
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/role_queryByEid")
    @ResponseBody
    public List<Long> queryByEid(Long eid) {
        return employeeService.queryByEid(eid);
    }

    @RequestMapping("/employee_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            employeeService.updateStateByPrimaryKey(id);
            result = new AjaxResult("离职成功", true);
        } catch (Exception e) {
            result = new AjaxResult("离职失败，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/employee_update")
    @ResponseBody
    public AjaxResult update(Employee employee) {
        AjaxResult result = null;
        try {
            employeeService.updateByPrimaryKey(employee);
            result = new AjaxResult("更新成功", true);
        } catch (Exception e) {
            result = new AjaxResult("更新失败，请联系管理员", false);
        }
        return result;
    }

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
            result = new AjaxResult("保存成功", true);
        } catch (Exception e) {
            result = new AjaxResult("保存失败，请联系管理员", false);
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
    public String index() {
        return "employee";
    }

    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {
        // 由于登录请求不经过拦截器，因此在LogUtils中获取不到request对象，因此在登录中向threadLocal中设置request
        UserContext.set(request);
        AjaxResult result = null;
        Employee user = employeeService.getEmployeeForLogin(username, password);
        if (user != null) {
            request.getSession().setAttribute(UserContext.USER_IN_SESSION, user);
            // 根据员工id查询所有的权限集合，存入session
            List<String> userPermissions = permissionService.queryByEid(user.getId());
            request.getSession().setAttribute(UserContext.PERMISSION_IN_SESSION, userPermissions);
            // 登录成功同时查询所有的菜单信息存入session中
            List<Menu> menus = menuService.queryForRoot();
            PermissionUtil.checkMenuPermission(menus);
            request.getSession().setAttribute(UserContext.MENU_IN_SESSION, menus);
            result = new AjaxResult("登陆成功", true);
        } else {
            result = new AjaxResult("登陆失败，用户名或密码有误", false);
        }
        return result;
    }
}
