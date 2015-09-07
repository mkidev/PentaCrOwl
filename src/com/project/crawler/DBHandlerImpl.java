package com.project.crawler;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImpl implements DBHandler {
    private Session session;
    private Transaction transaction;

    public DBHandlerImpl(Session session) {
        this.session = session;
        transaction = session.beginTransaction();
    }

    @Override
    public Object save(Object object) {
        session.save(object);
        return object;
    }

    public Game getGameByName(Game game) {
        return (Game) session.createQuery("from game as g where g.name = ?").setString(0,game.getName()).uniqueResult();
    }

    public Object update(Object object) {
        session.saveOrUpdate(object);
        return object;
    }
    public <T> Object get(Class<T> clazz, Serializable id) {
        return session.get(clazz,id);
    }

    @Override
    public Game getGameByName(String name) {
        return (Game) session.createQuery("from Game where name=?").setString(0,name).uniqueResult();
    }

    @Override
    public Stream getStreamByName(String name) {
        return (Stream) session.createQuery("from Stream where name=?").setString(0,name).uniqueResult();
    }

    @Override
    public Channel getChannelByName(String name) {
        return (Channel) session.createQuery("from Channel where name=?").setString(0,name).uniqueResult();
    }

    @Override
    public void delete(Object object) {
        session.delete(object);
    }

    public void close(){
        transaction.commit();
        session.close();
    }
}
