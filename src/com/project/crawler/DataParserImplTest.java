package com.project.crawler;

import com.project.model.Game;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImplTest {

    @Test
    public void testParseChannels() throws Exception {

    }

    @Test
    public void testParseStreams() throws Exception {
        String s = "2015-09-07T05:29:43Z";
        String s1 = s.substring(0, 10);
        String s2 = s.substring(11, 19);

        System.out.println(s1 + " " + s2);
    }

    @Test
    public void testParseGames() throws Exception {
        DataCrawlerImpl crawler = new DataCrawlerImpl();
        DataParserImpl parser = new DataParserImpl(crawler);
        ArrayList<Game> games = parser.parseGames(crawler.getGames());
        games.forEach(game -> System.out.println(game.getName()));
    }
}