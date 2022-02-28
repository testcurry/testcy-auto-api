package com.testcy.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname AutoTools
 * @Description 接口测试常用工具方法
 * @Date 2022/2/28 17:33
 * @Created by qitongtong
 * @Version 1.0
 **/
public class AutoTools {


    public static String decodeUnicode(String unicodeStr) {
        //通过正则表达式匹配Unicode字符编码
        Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher matcher = pattern.matcher(unicodeStr);
        StringBuffer stringBuffer = new StringBuffer(unicodeStr.length());
        while (matcher.find()){

        }


        return null;
    }
}
