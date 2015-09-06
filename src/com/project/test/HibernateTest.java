package com.project.test;

import com.project.model.User;
import com.project.crawler.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by marcel on 15.04.2015.
 */
public class HibernateTest {
    public static void main(String[] args) {

        EntityManager session = HibernateUtil.getEntityManagerFactory().createEntityManager();

        User person = new User("Marcel");
        session.persist(person);

        session.getTransaction().commit();

        Query q = session.createQuery("From Person ");

        List<User> resultList = q.getResultList();
        System.out.println("num of employess:" + resultList.size());
        resultList.forEach(System.out::println);

        HibernateUtil.shutdown();

    }
}
