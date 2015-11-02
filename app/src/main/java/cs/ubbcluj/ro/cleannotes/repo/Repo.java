package cs.ubbcluj.ro.cleannotes.repo;

import java.util.List;

/**
 * Generic repository, stores T element with K as id.
 */
public abstract class Repo<T, K> {

    public abstract void add(T item);
    public abstract T find(K id);
    public abstract T update(K id);
    public abstract void delete(T item);
    public abstract List<T> findAll();
    public abstract int size();
}
