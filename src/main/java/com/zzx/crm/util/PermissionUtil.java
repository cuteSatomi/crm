package com.zzx.crm.util;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.domain.Menu;
import com.zzx.crm.domain.Permission;
import com.zzx.crm.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-29 9:57:34
 */
@Component
public class PermissionUtil {

    // 需要查询数据库得到所有的权限信息，因此需要注入PermissionService
    private static PermissionService permissionService;

    public static void checkMenuPermission(List<Menu> menus) {
        List<String> userPermissions = (List<String>) UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu menu = menus.get(i);
            if (StringUtils.isNotBlank(menu.getFunction())) {
                if (!userPermissions.contains(menu.getFunction())) {
                    menus.remove(i);
                }
            }
            if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                checkMenuPermission(menu.getChildren());
            }
        }
    }

    // @Autowired无法为静态成员变量注入，因此可以通过成员的set方法注入，注意该方法是非静态的
    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        PermissionUtil.permissionService = permissionService;
    }

    public static Boolean checkPermission(String function) {
        // 超级管理员可以访问任意资源
        Employee currentUser = (Employee) UserContext.get().getSession().getAttribute(UserContext.USER_IN_SESSION);
        if (currentUser.getAdmin()) {
            return true;
        }

        if (CommonUtils.permissionList.size() == 0) {
            // 如果存储所有权限的集合长度为0，则从数据库中查询封装到permissionList中
            List<Permission> permissions = permissionService.selectAll();
            for (Permission permission : permissions) {
                CommonUtils.permissionList.add(permission.getResource());
            }
        }

        if (CommonUtils.permissionList.contains(function)) {
            // 如果访问该资源需要对应的权限
            // 查看该用户是否拥有对应的权限
            List<String> userPermissions = (List<String>) UserContext.get().getSession().getAttribute(UserContext.PERMISSION_IN_SESSION);
            if (userPermissions.contains(function)) {
                // 如果当前用户拥有权限则放行
                return true;
            } else {
                // 如果没有对应的权限，则查看是否拥有ALL这个权限
                String allPermission = function.split(":")[0] + ":ALL";
                if (userPermissions.contains(allPermission)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            // 访问该资源无需权限，直接放行
            return true;
        }
    }
}
