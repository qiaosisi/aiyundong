package com.ranba.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户日志纪录
 * */
public class UserLog extends BasicEntity implements Serializable {
    private Integer id;  //ID
    private Integer user_id;  //用户ID
    /**
     * 新增日志类型
     * 1 登录
     * 2 注册
     * 3 上传视频
     * 4 短信验证码发送
     * 5 删除视频
     * 6 登录密码修改
     * 7 短信通知
     */
    private Integer operate_type;
    // 操作名称
    private String operate_name;
    // 具体操作内容
    private String operate_content;
    // 短信验证码
    private Integer code;
    // 操作IP
    private String operate_ip;
    // 操作时间
    private Date operate_time;
    // 版本信息
    private int v ;
    // 操作状态：1、成功；2、失败；
    private int operate_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(Integer operate_type) {
        this.operate_type = operate_type;
    }

    public String getOperate_name() {
        return operate_name;
    }

    public void setOperate_name(String operate_name) {
        this.operate_name = operate_name;
    }

    public String getOperate_content() {
        return operate_content;
    }

    public void setOperate_content(String operate_content) {
        this.operate_content = operate_content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getOperate_ip() {
        return operate_ip;
    }

    public void setOperate_ip(String operate_ip) {
        this.operate_ip = operate_ip;
    }

    public Date getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(Date operate_time) {
        this.operate_time = operate_time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getOperate_status() {
        return operate_status;
    }

    public void setOperate_status(int operate_status) {
        this.operate_status = operate_status;
    }
}
