package com.project.restServer;

import com.project.crawler.util.HibernateUtil;
import com.project.database.GameService;
import com.project.database.GameServiceImpl;
import com.project.database.UserService;
import com.project.database.UserServiceImpl;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by arash on 08.09.2015.
 */

public class UserController
{
    UserService service = new UserServiceImpl(HibernateUtil.getSessionFactory());
}
