package com.project.crawler;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import com.project.model.User;
import org.json.*;

import java.io.IOException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImpl implements DataParser {
    private DataCrawler crawler;

    public DataParserImpl(DataCrawler crawler){
        this.crawler = crawler;
    }

    public ArrayList<Channel> parseChannels(String game, String crawledChannelsData){
        JSONObject jsonChannel = new JSONObject(crawledChannelsData);
        JSONArray jsonArray = jsonChannel.getJSONArray("channels");
        ArrayList<Channel> channels = new ArrayList<Channel>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String name = jsonArray.getJSONObject(i).getString("name");
            int views = jsonArray.getJSONObject(i).getInt("views");
            int follower = jsonArray.getJSONObject(i).getInt("followers");
            String source = jsonArray.getJSONObject(i).getString("url");

            // ein weiterer HTTP-Request mit anschließender Verarbeitung wird benötigt
            // String group = jsonChannel.getJSONObject("stream").getJSONObject("channel").getJSONObject("_sources").getString("teams");

            channels.add(new Channel(name, views, follower, source));

        }

        return channels;
    }

    public ArrayList<Stream> parseStreams(String game, String crawledStreamsData) {
        JSONObject jsonStreams = new JSONObject(crawledStreamsData);
        JSONArray jsonArray = jsonStreams.getJSONArray("streams");
        ArrayList<Stream> streams = new ArrayList<Stream>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String source = jsonArray.getJSONObject(i).getJSONObject("_links").getString("self");
            String channel = jsonArray.getJSONObject(i).getJSONObject("channel").getString("name");
            int viewers = jsonArray.getJSONObject(i).getInt("viewers");

            //Greenwich time
            String createdAtString = jsonArray.getJSONObject(i).getString("created_at");
            String substringDate = createdAtString.substring(0, 10);
            String substringTime = createdAtString.substring(11, 19);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            Date createdAt = null;
            try {
                createdAt = format.parse(substringDate + " " + substringTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String previewPicture = jsonArray.getJSONObject(i).getJSONObject("preview").getString("template");

            streams.add(new Stream(source, channel, game, viewers, createdAt, previewPicture));
        }

        return streams;
    }


    public ArrayList<Game> parseGames(String crawledGamesData) {
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
