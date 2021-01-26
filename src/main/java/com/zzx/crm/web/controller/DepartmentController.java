package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Department;
import com.zzx.crm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zzx
 * @date 2021-01-26 20:42
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/department_queryForEmp")
    @ResponseBody
    public List<Department> queryForEmp() {
        List<Department> result = departmentService.queryForEmp();
        if (result != null && result.size() != 0) {
            return result;
        }
        return null;
    }

}
