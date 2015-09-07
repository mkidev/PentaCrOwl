package com.project.crawler;

import com.project.crawler.util.HibernateUtil;
import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import com.project.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DataOperator {
    private static DataCrawler dataCrawler;
    private static DataParser dataParser;
    private static DBHandler dbHandler;
    private String crawledGamesData = "";
    String games;
    String channels;
    String streams;
    public DataParser getDataParser() {
        return dataParser;
    }

    public DataCrawler getDataCrawler() {
        return dataCrawler;
    }

    public DBHandler getDbHandler(){
        return dbHandler;
    }

    public DataOperator() {
        dataCrawler = new DataCrawlerImpl();
        dataParser = new DataParserImpl(dataCrawler);
        dbHandler = new DBHandlerImpl(HibernateUtil.getSessionFactory().openSession());
    }
    public static void main(String[] args){
        DataOperator dataOperator = new DataOperator();
        dataOperator.saveGames();
        dataOperator.saveStream();
        dataOperator.saveChannel();
        dbHandler.close();
    }

    public void saveGames(){
        List<Game> games = new ArrayList<Game>();
        games = dataParser.parseGames(getCrawledGamesData());

        games.forEach(g ->
        {
            dbHandler.save(g);
        });
    }

    public void saveStream(){
        List<Game> game = new ArrayList<Game>();
        List<Stream> stream = new ArrayList<Stream>();
        List<String> gameName = new ArrayList<String>();
        game = dataParser.parseGames(getCrawledGamesData());
        game.forEach(g->
        {
            gameName.add(g.getName());
        });

        gameName.forEach(gn->
        {
            stream.addAll(dataParser.parseStreams(gn,dataCrawler.getStreams(gn)));
        });

        stream.forEach(s->
        {
            dbHandler.save(s);
        });

    }

    public void saveChannel(){
        List<Game> game = new ArrayList<Game>();
        List<Channel> channel = new ArrayList<Channel>();
        List<String> gameName = new ArrayList<String>();
        game = dataParser.parseGames(getCrawledGamesData());
        game.forEach(g->
        {
            gameName.add(g.getName());
        });

        gameName.forEach(gn->
        {
            channel.addAll(dataParser.parseChannels(gn, dataCrawler.getChannels(gn)));
        });

        channel.forEach(ch ->
        {
            dbHandler.save(ch);
        });
    }

    private String getCrawledGamesData(){
        if(crawledGamesData.equals("")) {
            crawledGamesData = dataCrawler.getGames();
        }
        return crawledGamesData;
    }
}
