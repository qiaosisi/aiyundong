package com.ranba.app;

import com.ranba.model.ApiResponse;
import com.ranba.model.Message;
import com.ranba.model.User;
import com.ranba.model.UserLog;
import com.ranba.service.*;
import com.ranba.util.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    UserService userService;

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
            logger.info("用户短信验证码格式不正确，code：",code);
            apiResponse.setCode(0);
            apiResponse.setMessage("请填写正确的短信验证码");
            return apiResponse;
        }

        // 查询5分钟之内的验证码
        String sendCode = messageService.selectCodeByPhone(phone);
        if (!code.equals(sendCode)){
            logger.info("用户短信验证不正确，code：",code);
            apiResponse.setCode(0);
            apiResponse.setMessage("请填写正确的短信验证码");
            return apiResponse;
        }

        // 密码加密
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String bcode = bc.encode(code);
        // 查询改手机号是否注册过
        int count = userService.selectByPhone(phone);

        User user = new User();
        user.setUsername(phone);
        user.setPassword(bcode);
        if (count == 0){
            // 手机号未注册
            user.setIp(IpUtil.getIpAddr(request));
            userService.insert(user);
        }else{
            userService.updateUser(user);
        }

        // 生成token
        Map<String, String> params = new HashMap<>();
        params.put("username", phone);
        params.put("password", code);
        params.put("grant_type", "password");
        String url = "http://localhost:8080/oauth/token";

        HttpClientUtil https = new HttpClientUtil();
        String resultStr = https.postToken(url, params);

        Map resultMap = JsonUtil.json2Obj(resultStr, Map.class);
        if (resultMap == null){
            logger.info("获取token失败，手机号：",phone);
            apiResponse.setCode(0);
            apiResponse.setMessage("获取token失败");
            return apiResponse;
        }

        apiResponse.setData(resultMap);
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
