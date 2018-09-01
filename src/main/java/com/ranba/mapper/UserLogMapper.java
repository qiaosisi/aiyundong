package com.ranba.mapper;

import com.ranba.model.UserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by WXC on 2016/11/25.
 */
@Mapper
public interface UserLogMapper {

    //添加日志
    @Insert("insert into user_log(user_id,operate_type,operate_name,operate_content,operate_status,operate_ip,operate_time) values(#{user_id},#{operate_type},#{operate_name},#{operate_content},#{operate_status},#{operate_ip},now())")
    public void insertUserLog(UserLog userLog);



    /*
    * 后台使用
    * */
    @Select("select * from user_log order by id desc limit #{offset},#{size}")
    public List<UserLog> listUserLogAdm(UserLog userLog);

    @Select("select count(*) from user_log")
    public int countUserLogAdm(UserLog userLog);
}
