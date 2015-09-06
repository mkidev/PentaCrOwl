package com.project.com.project.crawler;

import org.hibernate.Session;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImpl implements DBHandler {
    private Session session;

    public DBHandlerImpl(Session session) {
        this.session = session;
    }

    @Override
    public Object save(Object object) {
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public Object update(Object object) {
        session.beginTransaction();
        session.update(object);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public boolean delete(Object object) {
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();
        return true;
    }
}
