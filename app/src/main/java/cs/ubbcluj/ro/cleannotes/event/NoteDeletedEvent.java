package cs.ubbcluj.ro.cleannotes.event;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 02.11.2015.
 */
public class NoteDeletedEvent {
    public final Long id;

    public NoteDeletedEvent(Long id) {
        this.id = id;
        deleteNote();
    }

    private void deleteNote() {
        Note.findById(Note.class, id).delete();
    }
}
