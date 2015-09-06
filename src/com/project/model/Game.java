package com.project.model;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class Game {
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
