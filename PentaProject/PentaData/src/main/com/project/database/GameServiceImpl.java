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
        Game game = (Game) session.createCriteria(Game.class).add(Restrictions.like("name", gameName)).uniqueResult();
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
}
