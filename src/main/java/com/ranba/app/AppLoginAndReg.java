package com.ranba.app;

import com.ranba.model.ApiResponse;
import com.ranba.model.Message;
import com.ranba.model.UserLog;
import com.ranba.service.AdmMessageService;
import com.ranba.service.MessageService;
import com.ranba.service.PushService;
import com.ranba.service.UserLogService;
import com.ranba.util.IpUtil;
import com.ranba.util.RanbaSequenceUtil;
import com.ranba.util.StringUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class AppLoginAndReg {
    private static final Logger logger = LoggerFactory.getLogger(AppLoginAndReg.class);

    @Autowired
    AdmMessageService admMessageService;
    @Autowired
    PushService pushService;
    @Autowired
    MessageService messageService;
    @Autowired
    UserLogService userLogService;

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

    // 发送短信验证码 返回值：  0==失败 1==成功
    @PostMapping("/api/sendCode")
    @ResponseBody
    public ApiResponse sendCode(HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();
        String ip = IpUtil.getIpAddr(request);
        String phone = StringUtil.parsePhoneParam(request.getParameter("phone"));

        if(phone == null){
            logger.warn("用户获取短信验证码失败，参数不能为空！");
            apiResponse.setMessage("请输入正确的手机号！");
            apiResponse.setCode(0);
            return apiResponse;
        }

        // 计算当天发送的短信次数
        int count = messageService.countTodayAdmMessage(phone);
        if (count>=5){

            UserLog userLog = new UserLog();
            userLog.setUser_id(0);
            userLog.setOperate_name("短信通知");
            userLog.setOperate_type(1);
            userLog.setOperate_content(phone+"，发送短信验证码超过5次，不允许再发送。");
            userLogService.insertUserLog(userLog);

            apiResponse.setMessage("发送短信验证码超过5次，不允许再发送。");
            apiResponse.setCode(0);
            return apiResponse;
        }

        // 验证码
        String code = RanbaSequenceUtil.generateNumberString(6);
        boolean flag = pushService.pushMessage(0, 2, phone , code);
        if (flag) {
            // 信息表插入数据
            Message message = new Message();
            message.setCode(code);
            message.setPhone(phone);
            message.setIp(ip);
            messageService.insertMessage(message);
            logger.info("用户{}获取短信验证码成功！第{}次发送",phone,count+1);
            apiResponse.setMessage("获取短信验证码成功");
            apiResponse.setCode(1);
            return apiResponse;
        }

        logger.info("用户{}获取短信验证码失败，短信发送失败！",phone);
        apiResponse.setMessage("短信发送失败");
        apiResponse.setCode(0);
        return apiResponse;
    }
}
