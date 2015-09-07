package com.project.crawler;

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
        dataCrawler = new DataCrawlerImpl(this);
        dataParser = new DataParserImpl(this);
    }
    public static void main(String[] args){
        DataOperator dataOperator = new DataOperator();

        dataOperator.saveGames();
        dataOperator.saveStream();

    }

    public void saveGames(){
        List<Game> games = new ArrayList<Game>();
        games = dataParser.parseGames();

        games.forEach(g->
        {
            dbHandler.save(g);
        });
    }

    public void saveStream(){
        List<Game> game = new ArrayList<Game>();
        List<Stream> stream = new ArrayList<Stream>();
        List<String> gameName = new ArrayList<String>();
        game = dataParser.parseGames();
        game.forEach(g->
        {
            gameName.add(g.getName());
        });

        gameName.forEach(gn->
        {
            stream.addAll(dataParser.parseStreams(gn));
        });

        stream.forEach(s->
        {

            dbHandler.save(s);
        });

    }
}
