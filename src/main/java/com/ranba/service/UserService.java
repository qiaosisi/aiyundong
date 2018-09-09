package com.ranba.service;

import com.ranba.mapper.UserMapper;
import com.ranba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(userId);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List findAll() {
        List list = new ArrayList<>();
        userMapper.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public void delete(Long id){
        userMapper.delete(id);
    }

    public User save(User user){
        return userMapper.save(user);
    }

    // 根据手机号查找是否有该用户
    public int selectByPhone(String phone){
        return userMapper.selectByPhone(phone);
    }

    // 插入新用户
    public void insert(User user){
        userMapper.insert(user);
    }
}
