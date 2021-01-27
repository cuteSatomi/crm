package com.zzx.crm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzx
 * @date 2021-01-26 16:05:52
 */
public class UserContext {
    public static final String USER_IN_SESSION = "USER_IN_SESSION";

    private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
    public static void set(HttpServletRequest request) {
        threadLocal.set(request);
    }
    public static HttpServletRequest get() {
        return threadLocal.get();
    }
}
