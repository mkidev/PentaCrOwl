package com.project.database;

import com.project.model.User;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by arash on 11.09.2015.
 */
public class RegisterServiceTest
{
    static User user;
    RegisterService registerService = new RegisterService();
    Date date = new Date(9/11/2001);
    @Ignore
    @Test
    public void registerTest() throws Exception {
        registerService.register("lukas248","lukas248","pimmel@primel.com",date);
        assertEquals();
        System.out.println("Save passed.");
    }
}
