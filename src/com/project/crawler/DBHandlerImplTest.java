package com.project.crawler;

import com.project.crawler.util.HibernateUtil;
import com.project.model.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DBHandlerImplTest {

    DBHandler dbh;
    Game testGame;

    @Test
    public void testSave() throws Exception {
        assertEquals(testGame, dbh.save(testGame));
    }

    @Test
    public void testUpdate() throws Exception {
        assertEquals(testGame, dbh.update(testGame));
    }

    @Test
    public void testDelete() throws Exception {
        assertTrue(dbh.delete(testGame));
    }

    @Before
    public void setUp() throws Exception {
        testGame = new Game("LOL", 9999);
        dbh = new DBHandlerImpl(HibernateUtil.getEntityManagerFactory().createEntityManager());
    }

    @After
    public void tearDown() throws Exception {
    }
}