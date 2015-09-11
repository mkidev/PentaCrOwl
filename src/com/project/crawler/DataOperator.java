package com.project.crawler;

import com.project.crawler.util.HibernateUtil;
import com.project.database.DBHandler;
import com.project.database.DBHandlerImpl;
import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DataOperator {
    private static DataCrawler dataCrawler;
    private static DataParser dataParser;
    private static DBHandler dbHandler;
    private ArrayList<String> crawledGamesData = new ArrayList<>();

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
        dbHandler = new DBHandlerImpl(HibernateUtil.getSessionFactory());
    }
    public static void main(String[] args){
        /*int NUM_THREADS = 8;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(NUM_THREADS);

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("executed");
                DataOperator operator = new DataOperator();
                operator.operate();
            }
        }, 0, 3, TimeUnit.MINUTES);*/
        System.out.println("haha");
        DataOperator operator = new DataOperator();
        operator.operate();
        System.out.println("hihi");
    }

    @Scheduled(fixedDelay = 60000)
    public void operate(){
        DataOperator dataOperator = new DataOperator();
        dbHandler.startSession();
        dbHandler.startTransaction();
        dataOperator.saveGames();
        dbHandler.commit();
        dbHandler.closeSession();

        /*dbHandler.startSession();
        dbHandler.startTransaction();
        dataOperator.saveStream();
        dbHandler.commit();
        dbHandler.closeSession();*/

       /*  dbHandler.startSession();
        dbHandler.startTransaction();
        dataOperator.saveChannel();
        dbHandler.commit();
        dbHandler.closeSession();*/
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
            stream.addAll(dataParser.parseStreams(gn, dataCrawler.getStreams(gn)));
            
        });
        System.out.println("stream.size() = " + stream.size());
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

    private ArrayList<String> getCrawledGamesData(){
        if(crawledGamesData.isEmpty()) {
            crawledGamesData = dataCrawler.getGames();
        }
        return crawledGamesData;
    }
}
