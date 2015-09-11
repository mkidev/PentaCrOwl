package main.com.project.database;

import main.com.project.crawler.util.HibernateUtil;
import main.com.project.model.Game;
import org.junit.*;
import org.junit.runners.MethodSorters;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBHandlerImplTest extends Assert {

    DBHandler dbh;
    static Game testGame;

    @Ignore
    @Test
    public void test1Save() throws Exception {
        assertEquals(testGame, dbh.save(testGame));
        System.out.println("Save passed.");
    }


    @Ignore
    @Test
    public void test3Delete() throws Exception {
        dbh.delete(testGame);
        assertEquals(null, dbh.get(Game.class, new Long(1)));
        System.out.println("Delete passed.");

    }

    @Test
    public void test4aGetGameByname() throws Exception {
        Assert.assertEquals(testGame, dbh.getGameByName(testGame.getName()));
    }

    @Test
    public void test5CheckExists() throws Exception {
        assertEquals(true, dbh.checkExists(testGame));
    }

    @Before
    public void before() {
        dbh = new DBHandlerImpl(HibernateUtil.getSessionFactory());

    }

    @BeforeClass
    public static void setUp() throws Exception {
        testGame = new Game("League of Legends", 8888);
    }

    @After
    public void tearDown() throws Exception {
        dbh.close();
    }

}