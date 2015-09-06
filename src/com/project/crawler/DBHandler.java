package com.project.crawler;

/**
 * Created by Marcel Kisilowski on 06.09.15.
 */
public interface DBHandler {
    Object save(Object object);
    Object update(Object object);
    boolean delete(Object object);
}
