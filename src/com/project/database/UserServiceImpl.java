package com.project.database;

import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by arash on 08.09.2015.
 */
public class UserServiceImpl
{
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
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

    public User newUser(String nickName, String userName, String email, String password){
        User user = new User(nickName, userName, email, password);
        return user;
    }

    public void setEmail(String email){

    }

    public void uploadPicture(String picLink){

    }

    public void setBirthday(){

    }

    public boolean followChannel(){
        return false;
    }

    public boolean subscribeChannel(){
        return false;
    }

    public void deleteAccount(){

    }

    public void deleteHistory(){

    }

    public void deleteFriend(){

    }

    public void addFriend(){

    }

    public boolean available(){
        return false;
    }
}
