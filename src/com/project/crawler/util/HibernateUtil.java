package com.project.crawler.util;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 * Created by marcel on 15.04.2015.
 */
public class HibernateUtil {
    @PersistenceUnit
    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return Persistence.createEntityManagerFactory("");
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getEntityManagerFactory().close();
    }
}
