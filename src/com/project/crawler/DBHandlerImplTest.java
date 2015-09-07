package com.project.crawler;

import com.project.crawler.util.HibernateUtil;
import com.project.model.Game;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBHandlerImplTest {

    DBHandler dbh;
    static Game testGame;

    @Test
    public void test1Save() throws Exception {
        assertEquals(testGame, dbh.save(testGame));
        System.out.println("Save passed.");
    }

    @Ignore
    @Test
    public void test2Update() throws Exception {
        testGame = new Game("LOL", 1111);
        assertEquals(testGame, dbh.update(testGame));
        System.out.println("Update passed.");

    }
    @Ignore
    @Test
    public void test3Delete() throws Exception {
        dbh.delete(testGame);
        assertEquals(null, dbh.get(Game.class, new Long(1)));
        System.out.println("Delete passed.");

    }
    @Test
    public void test4GetGameByname() throws Exception {
        assertEquals(testGame, dbh.getGameByName(testGame.getName()));
    }

    @Before
    public void before() {
        dbh = new DBHandlerImpl(HibernateUtil.getSessionFactory().openSession());

    }

    @BeforeClass
    public static void setUp() throws Exception {
        testGame = new Game("LOL", 8888);
    }

    @After
    public void tearDown() throws Exception {
        dbh.close();
    }
}