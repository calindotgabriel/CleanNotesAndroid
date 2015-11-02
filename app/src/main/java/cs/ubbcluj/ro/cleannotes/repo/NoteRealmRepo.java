package cs.ubbcluj.ro.cleannotes.repo;

import android.content.Context;

import java.util.List;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by motan on 20.10.2015.
 */
public class NoteRealmRepo extends CRUDRepo<Note> {

    public static final String ID = "id";
    private final Realm mRealm;
    private final Context mContext;

    public NoteRealmRepo(Context context) {
        mContext = context;
        mRealm = Realm.getInstance(mContext);
    }

    /**
     * Adds or updates a note.
     */
    @Override
    public Note add(Note note) {
        mRealm.beginTransaction();
        note.setId(getNextKey());
        Note realmNote = mRealm.copyToRealm(note);
        mRealm.commitTransaction();
        return realmNote;
    }

    @Override
    public Note find(int id) {
        RealmQuery<Note> query = mRealm.where(Note.class);
        return query.equalTo(ID, id).findFirst();
    }

    @Override
    public Note update(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(int id) {
        mRealm.beginTransaction();
        find(id).removeFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void delete(Note item) {
        mRealm.beginTransaction();
        item.removeFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public List<Note> findAll() {
        return mRealm.allObjects(Note.class).subList(0, size());
    }

    @Override
    public int size() {
        return mRealm.allObjects(Note.class).size();
    }

    /**
     * Gets the next key.
     * Used for realm's auto increment.
     */
    private int getNextKey() {
        Number key = mRealm.where(Note.class).max(ID);
        boolean noObjectsFound = key == null;
        if (noObjectsFound) {
            return 0;
        }
        return key.intValue() + 1;
    }

    public void clearTable() {
        RealmUtils.cleanupTable(mContext, Note.class);
    }
}
