package com.aiyundong.service;

import com.aiyundong.mapper.MessageMapper;
import com.aiyundong.model.Message;
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
    public int countTodayAdmMessage(String phone,String day){return messageMapper.countTodayAdmMessage(phone,day);}
}
