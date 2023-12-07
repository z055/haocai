package com.haocai.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class MD5Util {

    public static final int ENCODE_TIME = 1024;

    /**
     * 获取随机盐
     * @param length 盐值长度
     * @return
     */
    public static String getSalt(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()_+=-/";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(76);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String md5(String str, String salt){
        str = salt + str + salt;
        for (int i = 0; i < ENCODE_TIME; i++) {
            str = DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }
}
