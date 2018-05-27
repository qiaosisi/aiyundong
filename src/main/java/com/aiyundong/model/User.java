package com.aiyundong.model;

import java.io.Serializable;

public class User extends BasicEntity implements Serializable {

    private int id;  //用户ID
    private String user_name;  //用户登录名(手机号)
    private String invite_code;  //用户邀请码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
