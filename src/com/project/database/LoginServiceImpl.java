package com.project.database;
/**
 * Created by macbook on 09.09.15.
 */

import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Date;








public class LoginServiceImpl  implements LoginService {
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;
    private UserService userService;

    public LoginServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        userService = new UserServiceImpl(sessionFactory);
    }


    @Override
    public Void login(String userName, String password) {
        return null;
    }

    @Override
    public Void logout(User user) {
        return null;
    }


    @Override
    public Void passwordForgot(String email) {
        return null;
    }
}
