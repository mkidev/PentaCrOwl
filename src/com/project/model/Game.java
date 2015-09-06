package com.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
@Entity
@Table
public class Game {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment" , strategy = "increment")
    private int gameID;
    private final String name;
    private final int viewers;

    public Game(String name, int viewers) {
        this.name = name;
        this.viewers = viewers;
    }

    public String getName() {
        return name;
    }

    public int getViewers() {
        return viewers;
    }
}
