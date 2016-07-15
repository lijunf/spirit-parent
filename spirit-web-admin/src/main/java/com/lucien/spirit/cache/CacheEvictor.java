package com.lucien.spirit.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;

/**
 * 缓存清理帮助类，注意方法命名规范.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午3:38:01
 * <p>Version: 1.0
 */
public class CacheEvictor {

    /**
     * 日志对象.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 清除字典缓存.
     */
    @CacheEvict(value = "dict", allEntries = true)
    public void clearDict() {
        logger.debug("清除字典缓存成功！");
    }

    /**
     * 清除系统参数缓存.
     */
    @CacheEvict(value = "config", allEntries = true)
    public void clearConfig() {
        logger.debug("清除系统参数缓存成功！");
    }

}
