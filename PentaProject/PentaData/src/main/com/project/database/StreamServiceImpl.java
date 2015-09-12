package com.project.database;

import com.project.model.Stream;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public class StreamServiceImpl implements StreamService {

    private Session session;
    private Transaction transaction;
    private SessionFactory sessionFactory;

    public StreamServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.session = sessionFactory.openSession();

    }

    @Override
    public List<Stream> getAllStreams() {
        List<Stream> streams = session.createCriteria(Stream.class).list();
        return streams;
    }

    @Override
    public List<Stream> getStreamsByGame(String game) {
        List<Stream> streams = session.createCriteria(Stream.class).add(Restrictions.eq("game", game)).list();
        return streams;
    }

    @Override
    public List<Stream> getTopStreams(int amount) {
        List<Stream> streams = session.createCriteria(Stream.class).addOrder(Order.desc("viewers")).setMaxResults(amount).list();
        return streams;
    }

    @Override
    public List<Stream> getNewestStreams(int amount) {
        List<Stream> streams = session.createCriteria(Stream.class).addOrder(Order.desc("createdAt")).setMaxResults(amount).list();
        return streams;
    }

    @Override
    public Stream getStreamByChannel(String channelName) {
        Stream stream = (Stream) session.createCriteria(Stream.class).add(Restrictions.eq("channel", channelName)).uniqueResult();
        return stream;
    }
}
