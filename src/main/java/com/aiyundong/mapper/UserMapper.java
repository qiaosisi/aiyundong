package com.aiyundong.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;

@Mapper
public interface UserDaoMapper {
    User findByUsername(String username);
}
