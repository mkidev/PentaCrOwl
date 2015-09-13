package com.project.database;

import com.project.model.Game;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public interface GameService {
    List<Game> getAllGames();

    Game getGame(String gameName);

    List<Game> getTopGames(int amount);

    List<Game> getGamesByPrefix(String prefix);
    public void flushGames();
    public Object save(Object object);
    public Transaction getTransaction();
    public void commitTransaction();
    public void beginTransaction();
}
