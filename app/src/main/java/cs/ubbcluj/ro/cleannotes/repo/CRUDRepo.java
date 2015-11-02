package cs.ubbcluj.ro.cleannotes.repo;

import java.util.List;

/**
 * Created by motan on 20.10.2015.
 */
public abstract class CRUDRepo<T> {

    public abstract T add(T item);
    public abstract T find(int id);
    public abstract T update(int id);
    public abstract void delete(int id);
    public abstract void delete(T item);
    public abstract List<T> findAll();
    public abstract int size();
}
