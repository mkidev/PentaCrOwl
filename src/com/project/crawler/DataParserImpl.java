package com.project.crawler;

import com.project.model.Game;
import com.project.model.Stream;
import com.project.model.User;
import org.json.*;

import java.util.ArrayList;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImpl implements DataParser {
    DataCrawler crawler;

    public DataParserImpl(DataCrawler crawler){
        this.crawler = crawler;
    }

    public User parseChannels(){
        return null;
    }

    public Stream parseStreams() {
        return null;
    }

    public ArrayList<Game> parseGames() {
        String crawledGamesData = crawler.getGames();

        JSONObject jsonGames = new JSONObject(crawledGamesData);
        JSONArray jsonArray = jsonGames.getJSONArray("top");
        ArrayList<Game> games = new ArrayList<Game>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String name = jsonArray.getJSONObject(i).getJSONObject("game").getString("name");
            int viewers = jsonArray.getJSONObject(i).getInt("viewers");

            games.add(new Game(name, viewers));
        }


        return games;
    }
}
