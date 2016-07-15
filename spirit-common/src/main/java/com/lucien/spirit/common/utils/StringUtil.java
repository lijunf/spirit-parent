package com.lucien.spirit.common.utils;

/**
 * 字符串处理帮助类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:45:26
 * <p>Version: 1.0
 */
public class StringUtil {

    /**
     * 判断字符串是否为空或空白字符.
     * @param str
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断字符串不为空和空白字符.
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }
}
