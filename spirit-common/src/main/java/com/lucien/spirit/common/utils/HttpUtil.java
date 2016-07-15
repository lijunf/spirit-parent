package com.lucien.spirit.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Http请求相关帮助类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:44:46
 * <p>Version: 1.0
 */
public class HttpUtil {

    /**
     * 获取HttpServletRequest对象.
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获得客户端真实IP地址.
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip; 
    } 
}
