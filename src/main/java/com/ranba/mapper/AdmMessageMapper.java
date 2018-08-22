package com.ranba.mapper;

import com.ranba.model.AdmMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ZKP on 2017/6/20.
 */
@Mapper
public interface AdmMessageMapper {
    /*
    * 后台使用
    * */
    //添加发送信息
    @Insert("insert into adm_message (phonenumber,message,status,type,create_time) values(#{phonenumber},#{message},#{status},#{type},now())")
    void insertAdmMessage(AdmMessage admMessage);

    //发送信息列表
    @Select("select * from adm_message order by id desc limit #{offset},#{size}")
    List<AdmMessage> listAdmMessage(AdmMessage admMessage);

    //发送信息总数
    @Select("select count(*) from adm_message")
    int countAdmMessage(AdmMessage admMessage);

    //删除信息
    @Delete("delete from adm_message where id = #{id}")
    void deleteAdmMessage(@Param("id") int id);

    /*
    *前台使用
    */

    //注册发送信息总数
    @Select("select count(*) from adm_message where type = 1 and phonenumber = #{phone}")
    int countRegAdmMessage(@Param("phone") String phone);
    //小程序每个手机当天发送短信总数
    @Select("select count(*) from adm_message where type = 0 and phonenumber = #{phone} and DAYOFMONTH(adm_message.create_time)=#{day}")
    int countTodayAdmMessage(@Param("phone") String phone, @Param("day") String day);
}
