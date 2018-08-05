package com.aiyundong.app;

import com.aiyundong.model.ApiResponse;
import com.aiyundong.util.StringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppLoginAndReg {
    private static final Logger logger = LoggerFactory.getLogger(AppLoginAndReg.class);

    /*
     * 发送短信验证码
     * */
    @PostMapping("/api/message")
    public ApiResponse message(String phone){
        ApiResponse apiResponse = new ApiResponse();

        if (StringUtils.isBlank(StringUtil.parsePhoneParam(phone))){
            apiResponse.setCode(0);
            apiResponse.setMessage("手机号码错误");
            return apiResponse;
        }

        apiResponse.setCode(1);
        return apiResponse;
    }

    /*
     * 接收登陆信息
     *  */
    @PostMapping("/api/login")
    public ApiResponse login(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();

        String phone = StringUtil.parsePhoneParam(request.getParameter("phone"));
        String code = StringUtil.parseStringParam(request.getParameter("code"));

        if (StringUtils.isBlank(phone)){
            logger.info("用户手机号码错误，phone：",phone);
            apiResponse.setCode(0);
            apiResponse.setMessage("请填写正确的手机号码");
            return apiResponse;
        }

        if (StringUtils.isBlank(code) || code.length() < 6){
            logger.info("用户短信验证码错误，code：",code);
            apiResponse.setCode(0);
            apiResponse.setMessage("请填写正确的短信验证码");
            return apiResponse;
        }

        apiResponse.setCode(1);
        return apiResponse;
    }
}
