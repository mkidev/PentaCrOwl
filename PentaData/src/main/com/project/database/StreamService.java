package com.project.database;

import com.project.model.Game;
import com.project.model.Stream;

import java.util.List;
import java.util.Objects;

/**
 * Created by Marcel Kisilowski on 08.09.15.
 */
public interface StreamService {
    List<Stream> getAllStreams();

    List<Stream> getStreamsByGame(String game);

    List<Stream> getTopStreams(int amount);

    List<Stream> getNewestStreams(int amount);

    Stream getStreamByChannel(String channelName);

   public Object save(Object object);

}
