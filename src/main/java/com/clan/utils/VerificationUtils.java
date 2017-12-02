package com.clan.utils;

/**
 * description:
 * author: magic
 * date: 2017/12/2 下午4:32
 */
public class VerificationUtils {
    /**
     * 参数非空校验
     * @param value
     * @return
     */
    public static String objectToString(Object value){
        String str = "";
        if(value != null){
            str = value.toString();
        }

        return str;
    }
}
