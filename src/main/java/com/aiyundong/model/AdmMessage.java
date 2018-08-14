package com.aiyundong.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ZKP on 2017/6/20.
 */
public class AdmMessage extends BasicEntity implements Serializable {

    private int id; //ID
    private String phonenumber; //手机号码
    private String message; //信息内容
    private int status; //状态
    private int type; //类型 0 其他 1 注册发送 2 找回密码发送
    private Date create_time;  //创建时间
    private Date update_time;  //更新时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
