package com.project.database;

import com.project.crawler.util.HibernateUtil;
import com.project.model.Channel;
import com.project.model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by arash on 08.09.2015.
 */
public class UserServiceImpl implements UserService
{
    private User user = new User();
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;
    private DBHandler dbHandler;

    public UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        dbHandler = new DBHandlerImpl(sessionFactory);
    }


    public User newUser(String nickName, String userName, String email, String password){
        User nUser = new User(nickName, userName, email, password);
        return nUser;
    }

    public void deleteAccount(String userName){
        transaction = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM user WHERE userName=" + userName);
        query.executeUpdate();
        transaction.commit();
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

    public int getNickAmount(String nickName)
    {
        List<User> user = new ArrayList<User>();
        Criteria crit = session.createCriteria(User.class).add(Restrictions.like("nickName", nickName));
        user = crit.list();

        return user.size() + 1;
    }

    public boolean checkIfUserExists(String userName)
    {
        List<User> user = new ArrayList<User>();
        boolean check = false;
        Criteria crit = session.createCriteria(User.class).add(Restrictions.like("userName", userName));
        user = crit.list();
        if(user.size() != 0)
        {
            check = true;
        }
        return check;
    }

    public void setEmail(String username, String email){
        User user = getUser(username);
        user.setEmail(email);
        dbHandler.save(user);
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

    @Override
    public void register(String nickName, String password, String email,Date date) {
        String userName = "";
        user.setEmail(email);
        user.setGeburtstag(date);
        user.setPassword(password);
        user.setNickName(nickName);
        boolean available = true;


        int usersize = getNickAmount(nickName);
        if (usersize < 10000) {
            int i = (int) (Math.random() * 8999) + 1000;
            while (available) {
                userName = nickName + "~" + i;
                i = (int) (Math.random() * 8999) + 1000;
                available = checkIfUserExists(userName) && i < 10000;
            }
        } else {
            int i = (int) (Math.random() * 10000) + 10000;

            while (available) {
                userName = nickName + "~" + i;
                i = (int) (Math.random() * 10000) + 10000;
                available = checkIfUserExists(userName) && i < 20000;
            }
        }
        user.setUserName(userName);
        transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }
}
