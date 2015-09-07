package com.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by arash on 07.09.2015.
 */
public class Channel {

    private String getGroup() {
        return group;
    }

    private void setGroup(String group) {
        this.group = group;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    private int id;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }



    private String name;
    private int follower;
    private String link;
    private String group;
    private int views;

    public Channel(String name, int views, int follower, String link) {
        this.name = name;
        this.views = views;
        this.follower = follower;
        this.link = link;
    }

    public Channel()
    {

    }

}
