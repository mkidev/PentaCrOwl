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
    private final String name;
    private final int viewers;
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long gameID;

    public Game() {
        name = "";
        viewers = 0;
    }

    public Game(String name, int viewers) {
        this.name = name;
        this.viewers = viewers;
    }

    public Game(String name, int viewers, long gameID) {
        this.gameID = gameID;
        this.name = name;
        this.viewers = viewers;
    }

    public String getName() {
        return name;
    }

    public int getViewers() {
        return viewers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        return name.equals(game.name);

    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", viewers=" + viewers +
                '}';
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
