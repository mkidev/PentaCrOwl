package com.project.crawler;

import com.project.database.*;
import com.project.database.util.HibernateUtil;
import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DataOperator {
    private static DataCrawler dataCrawler;
    private static DataParser dataParser;
    private static DBHandler dbHandler;
    private ArrayList<String> crawledGamesData = new ArrayList<>();

    private static GameService gameService;
    private static UserService userService;

    public DataParser getDataParser() {
        return dataParser;
    }

    public DataCrawler getDataCrawler() {
        return dataCrawler;
    }

    public DBHandler getDbHandler() {
        return dbHandler;
    }

    public DataOperator() {
        dataCrawler = new DataCrawlerImpl();
        dataParser = new DataParserImpl(dataCrawler);
        dbHandler = new DBHandlerImpl(HibernateUtil.getSessionFactory());

        gameService = new GameServiceImpl(HibernateUtil.getSessionFactory());
        userService = new UserServiceImpl(HibernateUtil.getSessionFactory());
    }

    public static void main(String[] args) {
        DataOperator dataOperator = new DataOperator();

        final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                dataOperator.operate();
            }
        }, 0, 5, TimeUnit.MINUTES);
    }
    public void operate(){
        dbHandler.startSession();
        dbHandler.startTransaction();
        saveGames();
        dbHandler.commit();
        dbHandler.closeSession();

        /*
        dbHandler.startSession();
        dbHandler.startTransaction();
        saveStream();
        dbHandler.commit();
        dbHandler.closeSession();

        dbHandler.startSession();
        dbHandler.startTransaction();
        saveChannel();
        dbHandler.commit();
        dbHandler.closeSession();
        */

        // TODO dbHandler.close();
    }

    public void saveGames() {
        List<Game> games = new ArrayList<Game>();
        games = dataParser.parseGames(getCrawledGamesData());

        games.forEach(g ->
        {
            dbHandler.save(g);
        });
    }

    public void saveStream() {
        List<Game> game = new ArrayList<Game>();
        List<Stream> stream = new ArrayList<Stream>();
        List<String> gameName = new ArrayList<String>();
        game = dataParser.parseGames(getCrawledGamesData());
        game.forEach(g ->
        {
            gameName.add(g.getName());
        });

        gameName.forEach(gn ->
        {
            stream.addAll(dataParser.parseStreams(gn, dataCrawler.getStreams(gn)));

        });
        System.out.println("stream.size() = " + stream.size());
        stream.forEach(s ->
        {

            dbHandler.save(s);
        });

    }

    public void saveChannel() {
        List<Game> game = new ArrayList<Game>();
        List<Channel> channel = new ArrayList<Channel>();
        List<String> gameName = new ArrayList<String>();
        game = dataParser.parseGames(getCrawledGamesData());
        game.forEach(g ->
        {
            gameName.add(g.getName());
        });

        gameName.forEach(gn ->
        {
            channel.addAll(dataParser.parseChannels(gn, dataCrawler.getChannels(gn)));
        });

        channel.forEach(ch ->
        {
            dbHandler.save(ch);
        });
    }

    private ArrayList<String> getCrawledGamesData() {
        if (crawledGamesData.isEmpty()) {
            crawledGamesData = dataCrawler.getGames();
        }
        return crawledGamesData;
    }
}
