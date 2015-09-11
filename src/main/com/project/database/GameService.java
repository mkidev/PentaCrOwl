package main.com.project.database;

import main.com.project.model.Game;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public interface GameService {
    List<Game> getAllGames();

    Game getGame(String gameName);

    List<Game> getTopGames(int amount);

    List<Game> getGamesByPrefix(String prefix);
}
