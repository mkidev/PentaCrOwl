package com.project.database;

import com.project.model.User;

/**
 * Created by arash on 08.09.2015.
 */
public interface UserService
{
    public User getUser(String userName);
    public User getNick(String nickName);
    public User getName(String name);
    public void newUser(String nickName, String userName, String email, String password);
    public void setEmail(String email);
    public void uploadPicture(String picLink);
    public void setBirthday();
    public boolean followChannel();
    public boolean subscribeChannel();
    public void deleteAccount();
    public void deleteHistory();
    public void deleteFriend();
    public void addFriend();
    public boolean available();

}
