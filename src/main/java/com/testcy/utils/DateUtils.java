package com.testcy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname DateUtils
 * @Description 日期时间工具类
 * @Date 2021/9/16 18:06
 * @Created by qitongtong
 * @Version 1.0
 **/
public class DateUtils {

    /**
     * 根据日期格式解析当前时间
     * @param format 日期格式
     * @return 返回解析之后的当前时间字符串
     */
    public static String createTime(String format) {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String formatDate = sdf.format(now);
            return formatDate;
        } catch (Exception e) {
            LoggerUtils.log.error("日期格式错误！");
            LoggerUtils.log.error("错误信息为："+e.fillInStackTrace());
            e.printStackTrace();
            return null;
        }
    }

}
