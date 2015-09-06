package com.project.crawler;

import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImpl implements DBHandler {
    private EntityManager entityManager;

    public DBHandlerImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Object save(Object object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public Object update(Object object) {
        entityManager.refresh(object);
        return object;
    }

    @Override
    public boolean delete(Object object) {
        entityManager.remove(object);
        return true;
    }
}
