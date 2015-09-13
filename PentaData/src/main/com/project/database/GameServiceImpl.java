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
    public void beginTransaction()
    {

        transaction = session.beginTransaction();
    }
    @Override
    public void commitTransaction()
    {
        transaction.commit();
    }
    @Override
    public Transaction getTransaction()
    {
        return transaction;
    }
    @Override
    public List<Game> getAllGames() {
        List games = session.createCriteria(Game.class).list();
        return games;
    }

    @Override
    public Game getGame(String gameName) {
        Game game = (Game) session.createCriteria(Game.class).add(Restrictions.like("name", gameName)).uniqueResult();
        return game;
    }
    @Override
    public Object save(Object object) {
        session.save(object);
        return object;
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
        SQLQuery query = session.createSQLQuery("DELETE FROM game");
        query.executeUpdate();
        transaction.commit();
        /*SQLQuery createTable = session.createSQLQuery("CREATE TABLE game(gameID BIGINT,name VARCHAR(255),preview VARCHAR(255), viewers INT,PRIMARY KEY (gameID))");
        createTable.executeUpdate();*/
    }

}
