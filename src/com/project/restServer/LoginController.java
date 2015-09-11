package com.project.restServer;

import com.project.crawler.util.HibernateUtil;
import com.project.database.LoginService;
import com.project.database.LoginServiceImpl;
import com.project.database.RegisterService;
import com.project.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by macbook on 09.09.15.
 */
@RestController
public class LoginController {
    LoginService service = new LoginServiceImpl(HibernateUtil.getSessionFactory());
    RegisterService reService = new RegisterService();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(String creds){
        System.out.println("creds = " + creds);

    }



    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(User user){

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(String email, String nickName, String password,Date date){
        reService.register(email, nickName, password, date);
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public void passwordForgot(String email){
        passwordForgot(email);
    }
}
