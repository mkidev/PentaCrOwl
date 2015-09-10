package com.project.database;

import com.project.crawler.DataOperator;
import com.project.crawler.util.HibernateUtil;
import com.project.model.User;

import java.util.Date;

/**
 * Created by arash on 10.09.2015.
 */
public class RegisterService {
    User user = new User();
    String userName= "";
    DataOperator dataOperator = new DataOperator();
    DBHandler dbHandler = dataOperator.getDbHandler();
    UserService userService = new UserServiceImpl(HibernateUtil.getSessionFactory());

    public void register(String nickName, String password, String email,Date date)
    {
        user.setEmail(email);
        user.setGeburtstag(date);
        user.setPassword(password);
        user.setNickName(nickName);
        if(dbHandler.checkExists(user.getNickName()))
        {


            int usersize = userService.getNickAmount(nickName);
            if(usersize < 10000){
            int i = (int)(Math.random() * 8999) + 1000;
            boolean available = true;
            while(available){
                userName = nickName + "~" +  i;
                i = (int)(Math.random() * 8999) + 1000;
                available = dbHandler.checkExists(userName) && i < 10000;
            }
            }
            else
            {
                int i = (int)(Math.random() * 10000) + 10000;
                boolean available = true;
                while(available){
                    userName = nickName + "~" +  i;
                    i = (int)(Math.random() * 10000) + 10000;
                    available = dbHandler.checkExists(userName) && i < 20000;
                }
            }
         user.setUserName(userName);

    }
    }
}
