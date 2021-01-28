package com.zzx.crm.web.controller;

import com.zzx.crm.page.PageResult;
import com.zzx.crm.query.PermissionQueryObject;
import com.zzx.crm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zzx
 * @date 2021-01-28 13:15:50
 */
@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permission_queryByRId")
    @ResponseBody
    public PageResult queryByRId(PermissionQueryObject qo) {
        return permissionService.queryForPage(qo);
    }

    @RequestMapping("/permission_list")
    @ResponseBody
    public PageResult list(PermissionQueryObject qo) {
        return permissionService.queryForPage(qo);
    }
}
