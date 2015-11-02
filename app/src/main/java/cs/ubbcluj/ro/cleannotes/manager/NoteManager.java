package cs.ubbcluj.ro.cleannotes.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.repo.NoteRealmRepo;

/**
 * Created by motan on 30.10.2015.
 */
public class NoteManager {

    private NoteRealmRepo mRepo;
    private List<Note> mNotesReference;

    public NoteManager(Context context) {
        mRepo = new NoteRealmRepo(context);
        mNotesReference = new ArrayList<>(mRepo.findAll());
    }

    public List<Note> getNotesReference() {
        return mNotesReference;
    }


    public void add(Note note) {
        mRepo.add(note);
        mNotesReference.add(note);
    }


    public void delete(int id) {
        mRepo.delete(id);
        for (Note note : mNotesReference) {
            if (note.getId() == id) {
                mNotesReference.remove(note);
            }
        }
    }

    public List<Note> findAll() {
        return mRepo.findAll();
    }
}
