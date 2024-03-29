package com.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by marcel on 15.04.2015.
 */
@Entity
@Table
public class Stream {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int streamID;
    private String source;
    @OneToOne
    private Channel channel;
    private String game;
    private int viewers;
    private Date createdAt;
    private String previewPicture;


    public Stream() {
    }

    public Stream(String source, Channel channel, String game, int viewers, Date createdAt, String previewPicture) {
        this.source = source;
        this.channel = channel;
        this.game = game;
        this.viewers = viewers;
        this.createdAt = createdAt;
        this.previewPicture = previewPicture;
    }

    public int getStreamID() {
        return streamID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getGame() {
        return game;
    }

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPreviewPicture() {
        return previewPicture;
    }
}
