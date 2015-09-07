package com.project.crawler;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
        session.update(object);
        return object;
    }
    public <T> Object get(Class<T> clazz, Serializable id) {
        return session.get(clazz,id);
    }

    @Override
    public Game getGameByName(String name) {
        return (Game) session.createQuery("from Game where name=:name").setString("name",name).uniqueResult();
    }

    @Override
    public Stream getStreamByName(String name) {
        return (Stream) session.createQuery("from Stream where name=:name").setString("name",name).uniqueResult();
    }

    @Override
    public Channel getChannelByName(String name) {
        return (Channel) session.createQuery("from Channel where name=:name").setString("name",name).uniqueResult();
    }

    @Override
    public void delete(Object object) {
        session.delete(object);
    }

    @Override
    public void close(){
        transaction.commit();
        session.close();
    }

    @Override
    public boolean checkExists(Object object) {
        boolean result = false;
        try {
            if (object instanceof Game) {
                Game gameToCheck = (Game) object;
                Game game = (Game) session.createCriteria(Game.class).add(Restrictions.eq("name", gameToCheck.getName())).uniqueResult();
                if(game!=null) {
                    result = true;
                }
            } else if (object instanceof Channel) {
                Channel channelToCheck = (Channel) object;
                Channel channel = (Channel) session.createCriteria(Channel.class).add(Restrictions.eq("name", channelToCheck.getName())).uniqueResult();
                if(channel!=null) {
                    result = true;
                }
            } else if (object instanceof Stream) {
                Stream streamToCheck = (Stream) object;
                Stream stream = (Stream) session.createCriteria(Stream.class).add(Restrictions.eq("channel", streamToCheck.getChannel())).uniqueResult();
                if(stream!=null) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
