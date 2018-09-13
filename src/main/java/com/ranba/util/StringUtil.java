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

    //过滤emoji 或者 其他非文字类型的字符
    public static String removeEmoji(String s){
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNotEmojiChar(c)) {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    private static boolean isNotEmojiChar(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    // 字符串格式化拼接 code输入字符串 begin 起始位数。middle 中间加*号的位数，end 最后保留的位数
    public static String maskNumber(String code, int begin, int middle, int end) {
        StringBuilder sb = new StringBuilder();
        if (code != null && !code.trim().equals("")) {
            int length = code.length();
            sb.append(code.substring(0, begin));
            if (middle != 0) {
                for (int i = 1; i <= middle; i++) {
                    sb.append("*");
                }
            }
            sb.append(code.substring(length - end, length));
        }
        return sb.toString();
    }
}
