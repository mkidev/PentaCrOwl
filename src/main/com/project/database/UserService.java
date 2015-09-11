package main.com.project.database;

import main.com.project.model.Channel;
import main.com.project.model.User;

import java.util.Date;

/**
 * Created by arash on 08.09.2015.
 */
public interface UserService {
    public User newUser(String nickName, String userName, String email, String password);

    public void deleteAccount(User user);

    public User getUser(String userName);

    public User getNick(String nickName);

    public User getName(String name);

    public void setEmail(User user, String email);

    public void uploadPicture(User user, String picLink);

    public void setBirthday(User user, Date geburtstag);

    public void changePassword(User user, String password);

    public void followChannel(User user, Channel channel);

    public void subscribeChannel(User user, Channel channel);

    public void addFriend(User user, User friend);

    public void deleteFriend(User user, User friend);

    public void deleteHistory(User user);

    public boolean available(User user);
}
