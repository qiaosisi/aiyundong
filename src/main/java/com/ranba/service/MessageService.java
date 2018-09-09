package com.ranba.service;

import com.ranba.mapper.MessageMapper;
import com.ranba.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    // 插入短信数据
    public void insertMessage(Message message){
        messageMapper.insertMessage(message);
    }

    // 每个手机当天发送短信总数
    public int countTodayAdmMessage(String phone){return messageMapper.countTodayAdmMessage(phone);}

    // 根据手机号查找验证码
    public String selectCodeByPhone(String phone){
        return messageMapper.selectCodeByPhone(phone);
    }
}
