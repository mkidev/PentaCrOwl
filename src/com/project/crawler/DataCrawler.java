package com.project.crawler;

import java.util.List;

/**
 * Created by arash on 06.09.2015.
 */
public interface DataCrawler {

    String getChannels();
    List<String> getStreams();
    String getGames();
}
