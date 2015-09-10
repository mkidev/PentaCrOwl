package com.project.database;
/**
 * Created by macbook on 09.09.15.
 */

import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.StringTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;






@Configuration
@EnableWebSecurity
public class LoginServiceImpl extends WebSecurityConfigurerAdapter implements LoginService {
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;
    private UserService userService;

    public LoginServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
        userService = new UserServiceImpl(sessionFactory);
    }

    public boolean authenticate(String authCredentials){

        if (null == authCredentials)
            return false;
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        // we have fixed the userid and password as admin
        // call some UserService/LDAP here

        User user = userService.getUser(username);
        boolean authenticationStatus = (user != null) && user.getPassword().equals(password);
        System.out.println("authenticationStatus = " + authenticationStatus);
        // boolean authenticationStatus = "admin".equals(username)
        //        && "admin".equals(password);

        return authenticationStatus;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("arash").password("arash");



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
    public Void register(String email, String nickName, String password, Date date) {
        return null;
    }

    @Override
    public Void passwordForgot(String email) {
        return null;
    }
}
