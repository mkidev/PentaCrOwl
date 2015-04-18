package com.project.crawler.test;

import com.project.crawler.model.Person;
import com.project.crawler.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by marcel on 15.04.2015.
 */
public class HibernateTest {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Person person = new Person("Marcel");
        session.save(person);

        session.getTransaction().commit();

        Query q = session.createQuery("From Person ");

        List<Person> resultList = q.list();
        System.out.println("num of employess:" + resultList.size());
        resultList.forEach(System.out::println);

    }
}
