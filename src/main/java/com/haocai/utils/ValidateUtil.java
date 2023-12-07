package com.haocai.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidateUtil {

    public static long[] toLongArray(String [] strings){
        if (ValidateUtil.isNotEmpty(strings)){
            long[] temp = new long[strings.length];
            for (int i = 0; i < strings.length; i++) {
                temp[i] = Long.parseLong(strings[i]);
            }
            return temp;
        }else{
            throw new NullPointerException("strings can not empty");
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                if (o.toString().trim().equals("")) {
                    return true;
                }
            } else if (o instanceof List) {
                if (((List)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Map) {
                if (((Map)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Set) {
                if (((Set)o).size() == 0) {
                    return true;
                }
            } else if (o instanceof Object[]) {
                if (((Object[])((Object[])o)).length == 0) {
                    return true;
                }
            } else if (o instanceof int[]) {
                if (((int[])((int[])o)).length == 0) {
                    return true;
                }
            } else if (o instanceof long[] && ((long[])((long[])o)).length == 0) {
                return true;
            }

            return false;
        }
    }
}
