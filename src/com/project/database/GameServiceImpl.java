package com.project.database;

import com.project.model.Game;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public class GameServiceImpl implements GameService {
    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;
    public GameServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();
    }
    @Override
    public List<Game> getAllGames() {
        List games = session.createCriteria(Game.class).list();
        return games;
    }

    @Override
    public Game getGame(String gameName) {
        Game game = (Game) session.createCriteria(Game.class).add(Restrictions.like("name",gameName)).uniqueResult();
        return game;
    }

    @Override
    public List<Game> getTopGames(int amount) {
        List<Game> games = session.createCriteria(Game.class).addOrder(Order.desc("viewers")).setMaxResults(amount).list();
        return games;
    }

    @Override
    public List<Game> getGamesByPrefix(String prefix) {
        List<Game> games = session.createCriteria(Game.class).add(Restrictions.like("name", prefix + "%")).list();
        return games;
    }

    @Override
    public void flushGames()
    {
        System.out.println("primel");
        SQLQuery query = session.createSQLQuery("DELETE FROM game");
        query.executeUpdate();
        /*SQLQuery createTable = session.createSQLQuery("CREATE TABLE game(gameID BIGINT,name VARCHAR(255),preview VARCHAR(255), viewers INT,PRIMARY KEY (gameID))");
        createTable.executeUpdate();*/
    }
}
