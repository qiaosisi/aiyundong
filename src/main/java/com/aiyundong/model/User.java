package com.aiyundong.model;

import java.io.Serializable;

public class User extends BasicEntity implements Serializable {

    private Integer id;  //用户ID
    private String username;  //用户登录名(手机号)
    private String password;  //用户邀请码
    private Integer salary;
    private Integer age;
    // 状态 0，1 as 删除，存在
    private Integer state;

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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
