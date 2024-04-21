package repository;

import java.util.List;

/**
 *
 * @author 22wili03
 */
public interface Dao<T> {

    T get(int id);

    List<T> getAll();

    boolean save(T t);

    void update(T t);

    void delete(T t);
}
