package com.ranba.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    //短信验证码
    /*------------start-----------*/
    @Value("${access.key.id}")
    private String access_key_id;

    @Value("${access.key.secret}")
    private String access_key_secret;

     /*------------end-----------*/

    public String getAccess_key_id() {
        return access_key_id;
    }

    public void setAccess_key_id(String access_key_id) {
        this.access_key_id = access_key_id;
    }

    public String getAccess_key_secret() {
        return access_key_secret;
    }

    public void setAccess_key_secret(String access_key_secret) {
        this.access_key_secret = access_key_secret;
    }
}
