package com.project.database;

import com.project.model.Channel;
import com.project.model.Game;
import com.project.model.Stream;

import java.io.Serializable;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public interface DBHandler {
    Object save(Object object);
    Object update(Object object);
    void delete(Object object);
    void closeSession();
    boolean checkExists(Object object);
    void close();

    <T> Object get(Class<T> gameClass, Serializable aLong);
    Game getGameByName(String name);
    Stream getStreamByName(String name);
    Channel getChannelByName(String name);
    void startSession();

    void commit();
    void startTransaction();
    Object saveOrUpdate(Object object);
}
