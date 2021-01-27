package com.zzx.crm.web.interceptor;

import com.zzx.crm.domain.Employee;
import com.zzx.crm.util.UserContext;
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
