package cs.ubbcluj.ro.cleannotes.event;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 02.11.2015.
 */
public class OnNotePressedEvent {

    public final Note note;

    public OnNotePressedEvent(Long id) {
        this.note = Note.findById(Note.class, id);
    }
}
