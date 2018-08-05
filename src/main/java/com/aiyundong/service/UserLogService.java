package com.aiyundong.service;

import com.aiyundong.mapper.UserLogMapper;
import com.aiyundong.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;
    //添加日志
    public void insertUserLog(UserLog userLog){
        userLogMapper.insertUserLog(userLog);
    }

    /*
    * 后台使用
    * */

    public List<UserLog> listUserLogAdm(UserLog userLog){
        return userLogMapper.listUserLogAdm(userLog);
    }

    public int countUserLogAdm(UserLog userLog){
        return userLogMapper.countUserLogAdm(userLog);
    }
}
