package com.project.crawler;

import org.hibernate.Session;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImpl implements DBHandler {
    private Session session;

    public DBHandlerImpl(Session entityManager) {
        this.session = entityManager;
    }

    @Override
    public Object save(Object object) {
        session.save(object);
        return object;
    }

    @Override
    public Object update(Object object) {
        session.update(object);
        return object;
    }

    @Override
    public boolean delete(Object object) {
        session.delete(object);
        return true;
    }
}
