package com.ranba.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static String parseStringParam(String text) {
        if (StringUtils.isNotBlank(text) && !text.equals("null") && !text.equals("undefined")){
            text = text.replaceAll("\\<[^>]*>", "").trim();
            if(StringUtils.isNotBlank(text)){
                return text;
            }
        }
        return null;
    }

    //转型输入的字符串为手机号
    public static String parsePhoneParam(String str) {
        String result = parseStringParam(str);
        if (result != null) {
            if(result.matches("^(13|14|15|16|17|18|19)[0-9]{9}$")){
                return result;
            }
        }
        return null;
    }
}
