package com.project.crawler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by arash on 06.09.2015.
 */
public class DataCrawlerImpl implements DataCrawler {
    private boolean _responseCheck;


    public DataCrawlerImpl() {


    }


    private String executeGet(String url) throws Exception {
        String result = "";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        System.out.println("url = " + url);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        _responseCheck = responseCode == 200;
        try {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            result = response.toString();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;

    }

    public ArrayList<String> getGames() {
        String GAMES_TOP_URL = "https://api.twitch.tv/kraken/games/top";
        ArrayList<String> games = new ArrayList<String>();
        String streamsOffset = "?limit=100&offset=";

        int maxOffSet = 0;
        maxOffSet = getMaxOffsetGames();

        for (int offset = 0; maxOffSet >= offset; offset = offset + 100) {
            if (maxOffSet - offset <= 100) {
                int rest = maxOffSet - offset;
                try {
                    games.add(executeGet(GAMES_TOP_URL + streamsOffset + rest + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    games.add(executeGet(GAMES_TOP_URL + streamsOffset + offset + ""));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return games;

    }


    public ArrayList<String> getChannels(String game) {
        ArrayList<String> channels = new ArrayList<String>();
        String channelsGeneral = "https://api.twitch.tv/kraken/search/channels?q=";
        String streamsOffset = "&limit=100&offset=";

        int maxOffSet = 0;
        maxOffSet = getMaxOffsetChannels(game);


        for (int offset = 0; maxOffSet >= offset; offset = offset + 100) {
            if (maxOffSet - offset <= 100) {

                int rest = maxOffSet - offset;
                try {
                    if (_responseCheck) {
                        channels.add(executeGet(channelsGeneral + URLEncoder.encode(game) + streamsOffset + rest + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    if (_responseCheck) {
                        channels.add(executeGet(channelsGeneral + URLEncoder.encode(game) + streamsOffset + offset + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return channels;
    }

    public ArrayList<String> getStreams(String game) {
        ArrayList<String> streams = new ArrayList<String>();

        String streamsGeneral = "https://api.twitch.tv/kraken/streams?game=";
        String streamsOffset = "&limit=100&offset=";

        int maxOffSet = 0;
        maxOffSet = getMaxOffsetStreams(game);


        for (int offset = 0; maxOffSet >= offset; offset = offset + 100) {
            if (maxOffSet - offset <= 100) {

                int rest = maxOffSet - offset;

                try {
                    if (_responseCheck) {
                        streams.add(executeGet(streamsGeneral + URLEncoder.encode(game) + streamsOffset + rest + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    if (_responseCheck) {
                        streams.add(executeGet(streamsGeneral + URLEncoder.encode(game) + streamsOffset + offset + ""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return streams;
    }

    public int getMaxOffsetStreams(String game) {
        JSONObject jsonStreams;
        int total = 0;
        String result = "Fehler";
        String streamsGeneral = "https://api.twitch.tv/kraken/streams?game=";
        try {
            result = executeGet(streamsGeneral + URLEncoder.encode(game));
            jsonStreams = new JSONObject(result);
            total = jsonStreams.getInt("_total");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public int getMaxOffsetChannels(String game) {
        JSONObject jsonStreams;
        int total = 0;
        String result = "Fehler";
        String channelGeneral = "https://api.twitch.tv/kraken/search/channels?q=";
        try {
            result = executeGet(channelGeneral + URLEncoder.encode(game));
            jsonStreams = new JSONObject(result);
            total = jsonStreams.getInt("_total");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public int getMaxOffsetGames() {
        JSONObject jsonStreams;
        int total = 0;
        String result = "Fehler";
        String gamesGeneral = "https://api.twitch.tv/kraken/games/top";

        try {
            result = executeGet(gamesGeneral);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonStreams = new JSONObject(result);
        total = jsonStreams.getInt("_total");


        return total;
    }


}
