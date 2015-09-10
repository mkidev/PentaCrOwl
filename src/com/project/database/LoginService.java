package com.project.database;

import com.project.model.User;

import java.util.Date;

/**
 * Created by macbook on 09.09.15.
 */
public interface LoginService  {
    Void login(String userName, String password);
    Void logout(User user);
    Void register(String email, String nickName, String password, Date date);
    Void passwordForgot(String email);
    boolean authenticate(String creds);

}
