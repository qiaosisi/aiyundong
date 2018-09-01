package com.ranba.mapper;

import com.ranba.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageMapper {

    // 插入短信
    @Insert("insert into message (phone,code,ip,create_time) values (#{phone},#{code},#{ip},now())")
    void insertMessage(Message message);

    //小程序每个手机当天发送短信总数
    @Select("select count(*) from message where phone  = #{phone} and create_time > curdate()")
    int countTodayAdmMessage(@Param("phone") String phone);
}
