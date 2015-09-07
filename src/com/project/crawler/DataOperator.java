package com.project.crawler;

import com.project.model.User;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public class DataOperator {
    private static DataCrawler dataCrawler;
    private static DataParser dataParser;
    String games;
    String channels;
    String streams;
    public DataParser getDataParser() {
        return dataParser;
    }

    public DataCrawler getDataCrawler() {
        return dataCrawler;
    }

    public DataOperator() {
        dataCrawler = new DataCrawlerImpl(this);
        dataParser = new DataParserImpl(this);
    }
    public static void main(String[] args){

        User user = dataParser.parseChannels();

    }
}
