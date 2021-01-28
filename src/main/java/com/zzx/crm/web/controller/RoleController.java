package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Role;
import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.RoleQueryObject;
import com.zzx.crm.service.RoleService;
import com.zzx.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-28 9:47:52
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/role_queryForEmp")
    @ResponseBody
    public List<Role> queryForEmp() {
        return roleService.selectAll();
    }

    @RequestMapping("/role_list")
    @ResponseBody
    public PageResult list(RoleQueryObject qo) {
        PageResult result = roleService.queryForPage(qo);
        return result;
    }

    @RequestMapping("/role_delete")
    @ResponseBody
    public AjaxResult delete(Long id) {
        AjaxResult result = null;
        try {
            roleService.deleteByPrimaryKey(id);
            result = new AjaxResult("删除成功", true);
        } catch (Exception e) {
            result = new AjaxResult("删除失败，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_update")
    @ResponseBody
    public AjaxResult update(Role role) {
        AjaxResult result = null;
        try {
            roleService.updateByPrimaryKey(role);
            result = new AjaxResult("更新成功", true);
        } catch (Exception e) {
            result = new AjaxResult("更新失败，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role_save")
    @ResponseBody
    public AjaxResult save(Role role) {
        AjaxResult result = null;
        try {
            roleService.insert(role);
            result = new AjaxResult("保存成功", true);
        } catch (Exception e) {
            result = new AjaxResult("保存失败，请联系管理员", false);
        }
        return result;
    }

    @RequestMapping("/role")
    public String index() {
        return "role";
    }
}
