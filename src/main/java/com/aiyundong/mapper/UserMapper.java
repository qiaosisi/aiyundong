package com.aiyundong.mapper;


import com.aiyundong.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user")
    List<User> findAll();

    @Update("UPDATE user SET state=0 where id = #{id}")
    void delete(Long id);

    @Insert("INSERT INTO User (username, password, salary, age) VALUES (#{username},#{password},#{salary},#{age}")
    User save(User user);
}
