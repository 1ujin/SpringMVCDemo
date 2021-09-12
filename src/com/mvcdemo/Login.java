package com.mvcdemo;

import java.io.Serializable;

public class Login implements Serializable {
    private static final long serialVersionUID = -4185268948494816729L;
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
