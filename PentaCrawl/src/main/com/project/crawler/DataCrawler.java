package com.project.crawler;

import java.util.ArrayList;

/**
 * Created by arash on 06.09.2015.
 */
public interface DataCrawler {

    ArrayList<String> getChannels(String game);

    ArrayList<String> getStreams(String game);

    ArrayList<String> getGames();
}
