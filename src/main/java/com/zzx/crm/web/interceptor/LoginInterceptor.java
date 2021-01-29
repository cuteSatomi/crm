package com.zzx.crm.web.interceptor;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.util.PermissionUtil;
import com.zzx.crm.util.UserContext;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author zzx
 * @date 2021-01-26 16:14:39
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将request设置进threadLocal，方便后续日志操作
        UserContext.set(request);
        Employee employee = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
        if (employee == null) {
            response.sendRedirect("/login.jsp");
            return false;
        }
        if (handler instanceof HandlerMethod) {
            // 登录成功后，增加对请求的权限控制
            HandlerMethod method = (HandlerMethod) handler;
            // 将当前请求转成url表达式
            String function = method.getBean().getClass().getName() + ":" + method.getMethod().getName();
            // 调用PermissionUtil的方法来判断当前用户是否有访问该资源的权限
            Boolean flag = PermissionUtil.checkPermission(function);
            if (flag) {
                return true;
            } else {
                if (method.getMethod().isAnnotationPresent(ResponseBody.class)) {
                    // 如果是ajax请求
                    // 方法上有@ResponseBody则认为是Ajax请求
                    response.sendRedirect("/nopermission.json");
                } else {
                    // 如果是页面，转发的nopermission页面
                    response.sendRedirect("/nopermission.jsp");
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
