package com.testcy.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname AutoUtils
 * @Description 生成随机字符串的几种方法
 * @Date 2021/12/2 10:36
 * @Created by qitongtong
 * @Version 1.0
 **/
public class AutoUtils {
    /**
     * 将字符串中所有的Unicode编码字符转换为中文
     * @param origin 包含Unicode编码字符的原始字符串
     * @return 返回字符串中所有Unicode编码转换为中文后的完整字符串
     */
    public static String decodeUnicode(String origin) {
        //通过正则表达式匹配Unicode字符编码
        Pattern pattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher matcher = pattern.matcher(origin);
        StringBuffer stringBuffer = new StringBuffer(origin.length());
        while (matcher.find()) {
            String group = matcher.group(1);
            /*
            1、Integer.parseInt(str,radix) 以2/4/8/16进制的方式将字符串转换为十进制
            2、(char)将该字符对应的16进制数字转换为字符，即该数字在Unicode编码表中对应的字符
            3、Character.toString()将该字符转换为字符串
            */
            String str = Character.toString((char) Integer.parseInt(group, 16));
            /*
             * appendReplacement方法：sb是一个StringBuffer，replaceContext待替换的字符串，
             * 这个方法会把匹配到的内容替换为replaceContext，并且把从上次替换的位置到这次替换位置之间的字符串也拿到，
             * 然后，加上这次替换后的结果一起追加到StringBuffer里（假如这次替换是第一次替换，那就是只追加替换后的字符串啦）。
             * */
            matcher.appendReplacement(stringBuffer, str);
        }
        /*
         * appendTail方法：sb是一个StringBuffer，这个方法是把最后一次匹配到内容之后的字符串追加到StringBuffer中。
         * 两个方法一起使用就可以达到所有替换或者替换第一个：
         * */
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }



    //可以指定某个位置是a-z、A-Z或是0-9
    public static String getRandomString2(int length){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                case 0:
                    result=Math.round(Math.random()*25+65);
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }

        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 字符串长度
     * @return 返回随机字符串
     */
    public static String  generateRandomString(int length){
        String randomStr = RandomStringUtils.random(length);
        return randomStr;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return str + "," + temp;
    }

    //获得指定数量的UUID
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }


}
