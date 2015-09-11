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
        System.out.println("main: Anfang");
        final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        DataOperator operator = new DataOperator();

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable: Anfang");
                operator.operate();
                System.out.println("Runnable: Ende");
            }
        }, 0, 1, TimeUnit.MINUTES);
        System.out.println("main: Ende");

        /*System.out.println("main no.1");
        DataOperator operator = new DataOperator();
        operator.operate();
        System.out.println("main no.2");*/
    }

    @Scheduled(fixedDelay = 60000)
    public void operate(){
        System.out.println("operate: Anfang");
        DataOperator dataOperator = new DataOperator();
        dbHandler.startSession();
        System.out.println("operate: Session gestartet");
        dbHandler.startTransaction();
        System.out.println("operate: Transaction gestartet");
        dataOperator.saveGames();
        System.out.println("operate: alle Spiele geholt");
        dbHandler.commit();
        System.out.println("operate: commit");
        dbHandler.closeSession();
        System.out.println("operate: Session beendet");

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
        System.out.println("operate: Ende");
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
