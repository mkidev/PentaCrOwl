package com.project.restServer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by arash on 08.09.2015.
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
