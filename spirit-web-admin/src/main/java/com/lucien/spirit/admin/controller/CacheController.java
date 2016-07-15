package com.lucien.spirit.admin.controller;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucien.spirit.cache.CacheEvictor;

@Controller
@RequestMapping("/system/cache")
public class CacheController {
    
    protected static final Logger logger = LoggerFactory.getLogger(CacheController.class);
    
    @Autowired
    private CacheEvictor cacheEvictor;

    /**
     * 刷新缓存
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "refresh/{cacheName}", produces = "text/html;charset=UTF-8")
    public String refreshCache(@PathVariable String cacheName) {
        if (StringUtils.isEmpty(cacheName))
            return "未获取到缓存名";
        try {
            if ("all".equals(cacheName)) {
                for (Method method : cacheEvictor.getClass().getDeclaredMethods()) {
                    if (method.isAnnotationPresent(CacheEvict.class)) {
                        try {
                            method.invoke(cacheEvictor);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                String methodName = "clear" + cacheName.substring(0, 1).toUpperCase() + cacheName.substring(1);
                cacheEvictor.getClass().getDeclaredMethod(methodName).invoke(cacheEvictor);
            }
            return "刷新缓存成功";
        } catch (Exception e) {
            logger.warn(e.getMessage(), e, false);
            return "刷新缓存失败";
        }
    }
}
