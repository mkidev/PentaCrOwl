package com.project.database;

import com.project.model.Channel;
import com.project.model.User;

import java.util.Date;

/**
 * Created by arash on 08.09.2015.
 */
public interface UserService {
    User newUser(String nickName, String userName, String email, String password);

    void deleteAccount(User user);

    User getUser(String userName);

    User getNick(String nickName);

    User getName(String name);

    void setEmail(User user, String email);

    void uploadPicture(User user, String picLink);

    void setBirthday(User user, Date geburtstag);

    void changePassword(User user, String password);

    void followChannel(User user, Channel channel);

    void subscribeChannel(User user, Channel channel);

    void addFriend(User user, User friend);

    void deleteFriend(User user, User friend);

    void deleteHistory(User user);

    boolean available(User user);
}
