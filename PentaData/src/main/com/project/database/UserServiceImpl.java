package com.project.database;

import com.project.model.Channel;
import com.project.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by arash on 08.09.2015.
 */
public class UserServiceImpl implements UserService {
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }


    public void register(String nickname, String password, String email, Date date){
        User user = new User(nickname, password, email, date);
        String username = "";

        int usersize = getNickAmount(nickname);
        boolean available = true;

        if(usersize < 10000){
            int randomInt = (int) (Math.random() * 8999) + 1000;
            while(available){
                username = nickname + "~" + randomInt;
                available = checkIfUserExits(username);
            }
        }
        else{
            int randomInt = (int) (Math.random() * 19999) + 10000;
            while(available){
                username = nickname + "~" + randomInt;
                available = checkIfUserExits(username);
            }
        }

        user.setUserName(username);
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public void deleteAccount(String userName) {
        transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM user WHERE userName=" + userName);
        query.executeUpdate();
        transaction.commit();
    }

    @Override
    public Object save(Object object) {
        session.save(object);
        return object;
    }

    public User getUser(String userName) {
        User user = (User) session.createCriteria(User.class).add(Restrictions.like("userName", userName)).uniqueResult();
        return user;
    }

    public User getNick(String nickName) {
        User user = (User) session.createCriteria(User.class).add(Restrictions.like("nickName", nickName)).uniqueResult();
        return user;
    }

    public User getName(String name) {
        User user = (User) session.createCriteria(User.class).add(Restrictions.or(Restrictions.like("name", name), Restrictions.like("vorName", name))).uniqueResult();
        return user;
    }


    public void setEmail(String username, String email) {
        transaction = session.beginTransaction();
        User user = getUser(username);
        user.setEmail(email);
        session.save(user);
        transaction.commit();
    }

    public void uploadPicture(User user, String picLink) {
        user.setPicture(picLink);
    }

    public void setBirthday(User user, Date geburtstag) {
        user.setGeburtstag(geburtstag);
    }

    public void changePassword(User user, String password) {

    }


    public void followChannel(User user, Channel channel) {
        user.followChannel(channel);
    }

    public void subscribeChannel(User user, Channel channel) {
        user.subscribeChannel(channel);
    }


    public void addFriend(User user, User friend) {

    }

    public void deleteFriend(User user, User friend) {

    }


    public void deleteHistory(User user) {

    }

    public boolean available(User user) {
        return false;
    }


    private int getNickAmount(String nickname){
        List<User> userList = new ArrayList<User>();
        Criteria crit = session.createCriteria(User.class).add(Restrictions.like("nickName", nickname));
        userList = crit.list();
        return userList.size();
    }

    private boolean checkIfUserExits(String username){
        List<User> userList = new ArrayList<User>();
        Criteria crit = session.createCriteria(User.class).add(Restrictions.like("userName", username));
        userList = crit.list();
        return userList.size() > 0;
    }
}
