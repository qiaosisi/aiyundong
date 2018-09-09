package com.ranba.model;

import java.io.Serializable;
import java.util.Date;

public class User extends BasicEntity implements Serializable {

    private Integer id;
    // 用户手机号
    private String username;
    // 用户密码
    private String password;
    // 登录IP
    private String ip;
    // 用户年龄
    private Integer age;
    //  创建时间
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
