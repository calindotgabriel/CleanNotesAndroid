package cs.ubbcluj.ro.cleannotes.repo;

import android.content.Context;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import io.realm.Realm;

/**
 * Created by motan on 20.10.2015.
 */
public class RealmUtils {

    /**
     * Cleans up the table corresponding to model's class.
     * @param context appContext
     * @param classToClean given class to be cleaned
     */
    public static void cleanupTable(Context context, Class<Note> classToClean) {
        Realm instance = Realm.getInstance(context);
        instance.beginTransaction();
        instance.allObjects(classToClean).clear();
        instance.commitTransaction();
    }


}
