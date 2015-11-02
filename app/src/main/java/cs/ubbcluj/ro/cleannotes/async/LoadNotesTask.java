package cs.ubbcluj.ro.cleannotes.async;

import android.os.AsyncTask;

import java.util.List;

import cs.ubbcluj.ro.cleannotes.event.OnNotesLoadedEvent;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import de.greenrobot.event.EventBus;

/**
 * Created by motan on 02.11.2015.
 */
public class LoadNotesTask extends AsyncTask<Void, Void, List<Note>> {

    @Override
    protected List<Note> doInBackground(Void... params) {
        return Note.listAll(Note.class);
    }

    @Override
    protected void onPostExecute(List<Note> notes) {
        EventBus.getDefault().post(new OnNotesLoadedEvent(notes));
    }
}
