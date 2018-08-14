package com.aiyundong.service;

import com.aiyundong.model.UserLog;
import com.aiyundong.third.sms.SendMsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService {
    private static final Logger logger = LoggerFactory.getLogger(PushService.class);
    @Autowired
    private ConfigService configService;
    @Autowired
    private UserLogService userLogService;

    private static final int VERIFICATION_CODE_MINUTE = 5;

    private static final String RESETPASSMESSAGE_CODE = "【燃吧】您本次的验证码是：##，请勿泄漏，" + VERIFICATION_CODE_MINUTE + "分钟内有效。";;
    
    private static final String RESETPASS_CODE = "【燃吧】尊敬的燃吧用户，您的登录密码修改成功，欢迎访问燃吧官网 lequlai.com";
    
    public static final String SMS_TAIL = "";

    /**
     * 用户消息推送
     * 
     * @param userId
     *            用户ID
     * @param contentType
     *            短信内容类型: 1、修改密码 2、注册登录验证码 3、群发短信
     *
     * @param phoneNum
     *            手机号、用户标识
     * @param verificationCode
     *            验证码
     */
    public boolean pushMessage(int userId, int contentType, String phoneNum, String verificationCode) {

        boolean isSendMsgSuccess = false;
        String msgContent;
        UserLog userLog = new UserLog();

        switch (contentType){
            case 1:
                msgContent = RESETPASS_CODE + SMS_TAIL;
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, msgContent,configService);
                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content(phoneNum+"，密码修改成功:"+msgContent);
                userLog.setV(0);
                if(isSendMsgSuccess){
                    userLog.setOperate_status(1);
                }else {
                    userLog.setOperate_status(2);
                }
                userLogService.insertUserLog(userLog);
                break;
            case 2:
                msgContent = RESETPASSMESSAGE_CODE.replaceFirst("##", verificationCode) + SMS_TAIL;
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, msgContent,configService);
                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content(phoneNum+"，找回密码短信验证码:"+msgContent);
                userLog.setV(0);
                if(isSendMsgSuccess){
                    userLog.setOperate_status(1);
                }else {
                    userLog.setOperate_status(2);
                }
                userLogService.insertUserLog(userLog);
                break;
            case 3:
                msgContent = verificationCode;
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, msgContent,configService);
                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content("群发短信通知信息:"+msgContent);
                userLog.setV(0);
                if(isSendMsgSuccess){
                    userLog.setOperate_status(1);
                }else {
                    userLog.setOperate_status(2);
                }
                userLogService.insertUserLog(userLog);
                break;
        }

        return isSendMsgSuccess;
    }
}
