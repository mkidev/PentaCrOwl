package com.project.database;

import com.project.model.Channel;
import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * Created by arash on 08.09.2015.
 */
public class UserServiceImpl implements UserService
{
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }


    public User newUser(String nickName, String userName, String email, String password){
        User user = new User(nickName, userName, email, password);
        return user;
    }

    public void deleteAccount(User user){

    }


    public User getUser(String userName){
        User user = (User) session.createCriteria(User.class).add(Restrictions.like("userName", userName)).uniqueResult();
        return user;
    }

    public User getNick(String nickName){
        User user = (User) session.createCriteria(User.class).add(Restrictions.like("nickName", nickName)).uniqueResult();
        return user;
    }

    public User getName(String name){
        User user = (User) session.createCriteria(User.class).add(Restrictions.or(Restrictions.like("name", name), Restrictions.like("vorName", name))).uniqueResult();
        return user;
    }


    public void setEmail(User user, String email){
        user.setEmail(email);
    }

    public void uploadPicture(User user, String picLink){
        user.setPicture(picLink);
    }

    public void setBirthday(User user, Date geburtstag){
        user.setGeburtstag(geburtstag);
    }

    public void changePassword(User user, String password){

    }


    public void followChannel(User user, Channel channel){
        user.followChannel(channel);
    }

    public void subscribeChannel(User user, Channel channel){
        user.subscribeChannel(channel);
    }


    public void addFriend(User user, User friend){

    }

    public void deleteFriend(User user, User friend){

    }


    public void deleteHistory(User user){

    }

    public boolean available(User user){
        return false;
    }
}
