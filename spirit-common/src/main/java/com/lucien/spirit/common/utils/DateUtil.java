package com.lucien.spirit.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lucien.spirit.common.constants.FormatConstant;

/**
 * 日期对象处理帮助类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:44:00
 * <p>Version: 1.0
 */
public class DateUtil {

    /**
     * 格式化日期.
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String sDate = null;
        if (date != null) {
            if (pattern == null)
                pattern = FormatConstant.DEFALUT_DATE_PATTERN;
            
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            sDate = formatter.format(date);
        }
        return sDate;
    }

    /**
     * 根据默认日期格式yyyy-MM-dd格式化.
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, FormatConstant.DEFALUT_DATE_PATTERN);
    }

    /**
     * 将字符串转化为日期.
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr != null && dateStr.trim().length() > 0) {
            try {
                DateFormat format = new SimpleDateFormat(pattern);
                Date date = format.parse(dateStr);
                return date;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 将字符串转化为日期，默认日期格式yyyy-MM-dd.
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, FormatConstant.DEFALUT_DATETIME_PATTERN);
    }
}
