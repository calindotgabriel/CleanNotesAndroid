package cs.ubbcluj.ro.cleannotes.repo;

/**
 * Created by motan on 02.11.2015.
 */
public abstract class PersistentRepo<T, K> extends Repo<T, K> {

    public abstract void clear();
}
