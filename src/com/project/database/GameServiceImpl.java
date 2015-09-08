package com.project.database;

import com.project.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public class GameServiceImpl implements GameService {
    Session session;
    Transaction transaction;
    SessionFactory sessionFactory;
    public GameServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Game> getAllGames() {
        session = sessionFactory.openSession();
        List games = session.createCriteria(Game.class).list();
        session.close();
        return games;
    }

    @Override
    public Game getGame(String gameName) {
        session = sessionFactory.openSession();
        Game game = (Game) session.createCriteria(Game.class).add(Restrictions.like("name",gameName)).uniqueResult();
        session.close();
        return game;
    }

    @Override
    public List<Game> getTopGames(int amount) {
        session = sessionFactory.openSession();
        List<Game> games = session.createCriteria(Game.class).addOrder(Order.desc("viewers")).setMaxResults(amount).list();
        session.close();
        return games;
    }

    @Override
    public List<Game> getGamesByPrefix(String prefix) {
        session = sessionFactory.openSession();
        List<Game> games = session.createCriteria(Game.class).add(Restrictions.like("name", prefix+"%")).list();
        session.close();
        return games;
    }
}
