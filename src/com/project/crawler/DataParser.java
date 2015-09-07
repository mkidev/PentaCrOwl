package com.project.crawler;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;
import com.project.model.User;

import java.util.ArrayList;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public interface DataParser {
    ArrayList<Channel> parseChannels(String game, String crawledChannelsData);
    ArrayList<Stream> parseStreams(String game, String crawledStreamData);
    ArrayList<Game> parseGames(String crawledGameData);
}
