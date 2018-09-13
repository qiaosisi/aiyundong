package com.ranba.service;

import com.ranba.mapper.AdmMessageMapper;
import com.ranba.model.AdmMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmMessageService {

    @Autowired
    private AdmMessageMapper admMessageMapper;
    /*
    * 后台使用
    * */

    //添加发送信息
    public void insertAdmMessage(AdmMessage admMessage){
        admMessageMapper.insertAdmMessage(admMessage);
    }

    //发送信息列表

    public List<AdmMessage> listAdmMessage(AdmMessage admMessage){
        return admMessageMapper.listAdmMessage(admMessage);
    }

    //发送信息总数
    public int countAdmMessage(AdmMessage admMessage){
        return admMessageMapper.countAdmMessage(admMessage);
    }

	//信息删除
    public void deleteAdmMessage (int id){
        admMessageMapper.deleteAdmMessage(id);
    }


    /*
    *前台使用
    */

    //注册发送信息总数
    public int countRegAdmMessage(String phone){return admMessageMapper.countRegAdmMessage(phone);}
    //小程序每个手机当天发送短信总数
    public int countTodayAdmMessage(String phone,String day){return admMessageMapper.countTodayAdmMessage(phone,day);}

}
