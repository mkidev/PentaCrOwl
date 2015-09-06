package com.project.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arash on 06.09.2015.
 */
public class DataCrawlerImpl {
    String _streamerResults ="Fehler";
    String _channelResults ="Fehler";


    public static void main(String[] args) throws IOException {
        try {
            new DataCrawlerImpl().getGames();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private String executeGet(String url) throws Exception {
        String result;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return result = response.toString();

    }

    public String getGames(){
        String result="Fehler";
        String GAMES_TOP_URL = "https://api.twitch.tv/kraken/games/top";
        try {
           result = executeGet(GAMES_TOP_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public String getStreams(){
        String result="Fehler";
        String GAMES_TOP_URL = "https://api.twitch.tv/kraken/games/top";
        try {
            result = executeGet(GAMES_TOP_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }





}