package com.project.crawler;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;

import java.util.ArrayList;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public interface DataParser {
    ArrayList<Channel> parseChannels(String game, ArrayList<String> crawledChannelsData);

    ArrayList<Stream> parseStreams(String game, ArrayList<String> crawledStreamData);

    ArrayList<Game> parseGames(ArrayList<String> crawledGameData);
}
