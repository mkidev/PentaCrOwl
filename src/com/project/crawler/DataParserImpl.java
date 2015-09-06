package com.project.crawler;

import com.project.model.Game;
import com.project.model.Stream;
import com.project.model.User;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImpl implements DataParser {
    private DataCrawler crawler;

    public DataParserImpl(DataOperator operator){
        this.crawler = operator.getDataCrawler();
    }

    public User parseChannels(){
        return null;
    }

    public ArrayList<Stream> parseStreams(String game) {
        String crawledStreamsData = crawler.getStreams(game);

        JSONObject jsonGames = new JSONObject(crawledStreamsData);
        JSONArray jsonArray = jsonGames.getJSONArray("streams");
        ArrayList<Stream> streams = new ArrayList<Stream>();

        for (int i = 0; i < jsonArray.length(); i++) {
            //String source = jsonArray.getJSONObject(i).getJSONObject(links);
            //String channel = jsonArray.getJSONObject(i).getJSONObject("game").getString("name");
            Date createdAt = null;
            String previewPicture = null;

            //streams.add(new Stream(source, channel, game, createdAt, previewPicture));
        }

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
