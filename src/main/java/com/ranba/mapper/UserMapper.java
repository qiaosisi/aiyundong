package com.ranba.mapper;


import com.ranba.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user")
    List<User> findAll();

    @Update("UPDATE user SET state=0 where id = #{id}")
    void delete(Long id);

    @Insert("INSERT INTO user (username, password, ip, age,create_time) VALUES (#{username},#{password},#{ip},#{age},now())")
    User save(User user);

    // 根据手机号查找是否有该用户
    @Select("select * from user where username = #{username}")
    User selectByPhone(String username);

    @Insert("INSERT INTO user (username, password, ip, age,create_time) VALUES (#{username},#{password},#{ip},#{age},now())")
    @SelectKey(statement="SELECT LAST_INSERT_ID() AS id", keyProperty="id", before=false, resultType=java.lang.Integer.class)
    void insert(User user);

    // 更新用户密码
    @Update("UPDATE user SET password= #{password} where username = #{username}")
    void updateUser(User user);
}
