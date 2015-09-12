package crawler.util;

/**
 * Created by marcel on 18.04.2015.
 */
public interface DatabaseUtil<E> {
    public E getValue(E identifier);

    public boolean saveValue(E objectToSave);
}
