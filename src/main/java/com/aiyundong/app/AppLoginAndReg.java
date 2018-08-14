package com.aiyundong.app;

import com.aiyundong.model.AdmMessage;
import com.aiyundong.model.ApiResponse;
import com.aiyundong.service.AdmMessageService;
import com.aiyundong.service.PushService;
import com.aiyundong.util.IpUtil;
import com.aiyundong.util.RanbaSequenceUtil;
import com.aiyundong.util.StringUtil;
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

    //发送短信验证码 返回值：-1==电话号为空  0==短信验证码发送成功 1==超过五次不发送 99==发送失败
    @PostMapping("/api/sendCode")
    @ResponseBody
    public String sendCode(javax.servlet.http.HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        String phone = StringUtil.parseStringParam(request.getParameter("phone"));

        if(phone == null){
            logger.warn("用户获取短信验证码失败，参数不能为空！");
            return "-1";
        }

        //查看手机号是否发送过短信
        Calendar c=Calendar.getInstance();
        String day=String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        //计算当天发送的短信次数
        int count = admMessageService.countTodayAdmMessage(phone,day)+1;
        if (count>5){
            logger.info("用户{}发送短信验证码超过5次，不允许再发送。",phone);
            return "1";
        }
        //查询memcached是否有存在的验证码
        // String code = stringRedisTemplate.opsForValue().get("lequlai:string:vm_"+phone);
        String code="0";
        if(StringUtils.isBlank(code)){
            // 随机出来的验证码
            code = RanbaSequenceUtil.generateNumberString(6);
        }
        boolean flag;
        flag = pushService.pushMessage(0, 2, phone , code);

        if (flag) {
            //信息表插入数据
            AdmMessage admMessage = new AdmMessage();
            admMessage.setPhonenumber(phone);
            admMessage.setMessage("【乐趣来】您本次的注册验证码是："+code+"，请勿泄漏，5分钟有效。");
            admMessage.setStatus(1);
            admMessage.setType(0);
            admMessageService.insertAdmMessage(admMessage);

//            stringRedisTemplate.delete("lequlai:string:vm_"+phone);
//            stringRedisTemplate.opsForValue().set("lequlai:string:vm_"+phone, code, 300, TimeUnit.SECONDS);
            logger.info("用户{}获取短信验证码成功！第{}次发送",phone,count);

            return "0";
        }
        logger.info("用户{}获取短信验证码失败，短信发送失败！",phone);
        return "99";
    }
}
