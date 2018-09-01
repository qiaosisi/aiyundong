package com.ranba.service;

import com.ranba.model.UserLog;
import com.ranba.third.sms.SendMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushService {

    @Autowired
    private ConfigService configService;
    @Autowired
    private UserLogService userLogService;

    /**
     * 用户消息推送
     * 
     * @param userId 用户ID
     * @param contentType 短信内容类型: 1、修改密码 2、注册登录验证码 3、群发短信
     * @param phoneNum 手机号、用户标识
     * @param code 验证码
     */
    public boolean pushMessage(int userId, int contentType, String phoneNum, String code) {

        boolean isSendMsgSuccess = false;
        UserLog userLog = new UserLog();

        switch (contentType){
            case 1:
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, code,configService);
                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content(phoneNum+"，密码修改成功:"+code);
                if(isSendMsgSuccess){
                    userLog.setOperate_status(1);
                }else {
                    userLog.setOperate_status(2);
                }
                userLogService.insertUserLog(userLog);
                break;
            case 2:
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, code,configService);

                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content(phoneNum+"，登录注册短信验证码:"+code);
                if(isSendMsgSuccess){
                    userLog.setOperate_status(1);
                }else {
                    userLog.setOperate_status(2);
                }
                userLogService.insertUserLog(userLog);
                break;
            case 3:
                isSendMsgSuccess = SendMsgUtil.SendMsg(phoneNum, code,configService);
                //短信日志
                userLog.setUser_id(userId);
                userLog.setOperate_name("短信通知");
                userLog.setOperate_type(99);
                userLog.setOperate_content("群发短信通知信息:"+code);
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
