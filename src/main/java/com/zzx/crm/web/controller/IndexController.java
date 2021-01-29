package com.zzx.crm.web.controller;

import com.zzx.crm.domain.Menu;
import com.zzx.crm.service.MenuService;
import com.zzx.crm.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzx
 * @date 2021-01-25 17:18:40
 */
@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> queryForMenu(HttpSession session) {
        return (List<Menu>) session.getAttribute(UserContext.MENU_IN_SESSION);
    }
}
