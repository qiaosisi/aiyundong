package com.aiyundong.service;

import com.aiyundong.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService{
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
}
