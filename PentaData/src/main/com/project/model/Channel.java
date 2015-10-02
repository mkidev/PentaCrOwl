package com.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by arash on 07.09.2015.
 */
@Entity
@Table
public class Channel {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int channelID;
    private String name;
    private int follower;
    private String source;
    private int views;
    @OneToOne
    private Stream stream;

    public Channel() {
    }

    public Channel(String name, int views, int follower, String source) {
        this.name = name;
        this.views = views;
        this.follower = follower;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }


    public Stream getStream() {
        return stream;
    }

    public void setStream(Stream stream) {
        this.stream = stream;
    }
}
