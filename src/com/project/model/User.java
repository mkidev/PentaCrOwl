package com.project.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private String vorname;
    private int plattform;
    private Date geburtstag;
    private String picture;

    private ArrayList<Channel> followedChannels;
    private ArrayList<Channel> subscribedChannels;
    private ArrayList<Channel> historyChannels;

    public User(){

    }

    public User(String nickName, String userName, String email, String password){
        this.nickName = nickName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public int getPlattform() {
        return plattform;
    }

    public void setPlattform(int plattform) {
        this.plattform = plattform;
    }

    public Date getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public ArrayList<Channel> getFollowedChannels() {
        return followedChannels;
    }

    public void followChannel(Channel channel) {
        this.followedChannels.add(channel);
    }

    public ArrayList<Channel> getSubscribedChannels() {
        return subscribedChannels;
    }

    public void subscribeChannel(Channel channel) {
        this.subscribedChannels.add(channel);
    }

    public ArrayList<Channel> getHistoryChannels() {
        return historyChannels;
    }

    public void addToHistory(Channel channel) {
        this.historyChannels.add(channel);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString(){
        return "Person[id:"+ userID +", name:"+name + "]";
    }
}