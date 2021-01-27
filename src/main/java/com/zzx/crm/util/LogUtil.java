package com.zzx.crm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzx.crm.domain.Employee;
import com.zzx.crm.domain.Log;
import com.zzx.crm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author zzx
 * @date 2021-01-25 11:01:21
 */
public class LogUtil {

    @Autowired
    private LogService logService;

    public void writeLog(JoinPoint joinPoint) {
        // 防止AOP拦截logService本身，造成死循环
        if (joinPoint.getTarget() instanceof LogService) {
            return;
        }

        Log log = new Log();
        log.setOpTime(new Date());

        // 从threadLocal获取request对象
        HttpServletRequest request = UserContext.get();

        // 从session中获取当前操作的用户
        Employee currentUser = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);

        // 从request中获取当前操作的ip
        String ip = request.getRemoteAddr();

        // 获取用户操作的方法以及参数
        String function = joinPoint.getTarget().getClass().getName() + " : " + joinPoint.getSignature().getName();

        ObjectMapper mapper = new ObjectMapper();
        String params;
        try {
            params = mapper.writeValueAsString(joinPoint.getArgs());
            log.setParams(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.setFunction(function);
        log.setOpIp(ip);
        log.setOpUser(currentUser);
        // 将收集到的log对象存入数据库
        logService.insert(log);
    }
}
