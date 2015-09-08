package com.project.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marcel on 15.04.2015.
 */
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    private int userID;
    @NaturalId
    private String userName;
    private String nickName;
    private String email;
    private String password;
    private String name;
    private String vorName;
    private int plattform;
    private Date geburtsTag;
    private ArrayList<Channel> followedChannels;
    private ArrayList<Channel> subscribedChannels;
    private ArrayList<Channel> historyChannels;
    private String picture;

    public User(String nickName, String userName, String email, String password){
        this.nickName = nickName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }



    @Override
    public String toString(){
        return "Person[id:"+ userID +", name:"+name + "]";
    }
}