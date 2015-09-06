package com.project.crawler;

import java.util.List;

/**
 * Created by arash on 06.09.2015.
 */
public interface DataCrawler {

    String getChannels();
    String getStreams(String game);
    String getGames();
}
