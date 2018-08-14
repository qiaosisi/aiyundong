package com.aiyundong.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by XJY on 2017/3/2.
 */
public class RanbaSequenceUtil {
    private static final String NUMBER_CHAR = "1234567890";
    private static final String NUMBER_LETTER_CHAR = "1234567890abcdefghigklmnopqrstuvwxyz";

    public static String genCartNum() {
      return  UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 生成格式为 yyyyMMddHHmmss+3位随机数 的字符串 文件名
    public static String createFileNameStr() {
        StringBuilder sb = new StringBuilder();
        String code = generateNumberString(3);
        sb.append(DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG2));
        sb.append(code);
        return sb.toString();
    }

    public static String generateNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
        }
        return sb.toString();
    }

    //生成指定位大小写字母数字组合打印机设备码
    public static String generateRandomCode(int length){
        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            SecureRandom random = new SecureRandom();
            for (int i = 0; i < length; i++) {
                sb.append(NUMBER_LETTER_CHAR.charAt(random.nextInt(NUMBER_LETTER_CHAR.length())));
            }
            return sb.toString();
        } else {
            throw new RuntimeException("请输入一个大于0的数");
        }
    }

    /**
     * @Title: inviteCodeGenerate
     * @Description: 生成邀请码
     * @param @return 设定文件
     * @return String 返回类型
     */
    public static String inviteCodeGenerate() {
        String base = "qwertyuiopasdfghjklzxcvbnm";
        int length = 6;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //生成订单编号
    public static String createFlowNo(){
        StringBuilder sb = new StringBuilder("lql");
        sb.append(DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG3));
        String code = generateNumberString(6);
        sb.append(code);
        return sb.toString();
    }

    //生成优惠券编码
    public static String createCouponNo(){
        StringBuilder sb = new StringBuilder("cp");
        sb.append(DateTimeUtil.getNow(DateTimeUtil.FORMAT_LONG3));
        String code = generateNumberString(6);
        sb.append(code);
        return sb.toString();
    }

}
