package com.project.crawler;

import com.project.crawler.util.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImplTest {

    DBHandler dbh;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        dbh = new DBHandlerImpl(HibernateUtil.getSessionFactory().openSession());
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.shutdown();
    }
}