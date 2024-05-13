package com.example.mesuredeglycemie.model;

import com.example.mesuredeglycemie.controller.HomeController;

public class User {
private String email,password;

    public User(String email ,String password) {
        this.email=email;
        this.password=password;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
}
