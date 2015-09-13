package crawler;

import com.project.model.Game;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImplTest {
    DataParserImpl parser = new DataParserImpl(new DataCrawlerImpl());

    @Ignore
    @Test
    public void testParseChannels() throws Exception {

    }

    @Test
    public void testParseStreams() throws Exception {
        String data = "";
        ArrayList list = new ArrayList<>();
        parser.parseStreams("WildStar", list);
    }

    @Ignore
    @Test
    public void testParseGames() throws Exception {
        DataCrawlerImpl crawler = new DataCrawlerImpl();
        DataParserImpl parser = new DataParserImpl(crawler);
        ArrayList<Game> games = parser.parseGames(crawler.getGames());
        games.forEach(game -> System.out.println(game.getName()));
    }
}