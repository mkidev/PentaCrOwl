package com.project.database.util;

/**
 * Created by marcel on 18.04.2015.
 */
public interface DatabaseUtil<E> {
    E getValue(E identifier);

    boolean saveValue(E objectToSave);
}
