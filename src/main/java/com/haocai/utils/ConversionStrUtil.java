package com.haocai.utils;

import com.haocai.module.system.vo.User;

import java.lang.reflect.Field;

/**
 *   把实体类的空字符'' 转换为null
 */
public class ConversionStrUtil {
    public static Object ConversionStr(Object o) {

        for (Field declaredField : o.getClass().getSuperclass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            try {
              Object value= declaredField.get(o);
               if("".equals(value)){
                 declaredField.set(o,null);
               }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return o;
    }
}
