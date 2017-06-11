package com.baomidou.springboot.entity;

import java.io.Serializable;

/**
 * Created by huanglijun on 2017/6/10.
 */
public class UserHLJ implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

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

    public UserHLJ(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
