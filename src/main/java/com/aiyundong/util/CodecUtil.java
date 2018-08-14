package com.aiyundong.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


public class CodecUtil {


    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    //md5加密
    public final static String md5Hex(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            return new String(DigestUtils.md5Hex(s.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            logger.error("MD5加密错误:{}",e);
        }
        return null;
    }

    //SHA1加密
    public final static String sha1Hex(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            return new String(DigestUtils.sha1Hex(s.getBytes("UTF-8")));

        } catch (UnsupportedEncodingException e) {
            logger.error("SHA1加密错误:{}",e);
        }
        return null;
    }

}  
