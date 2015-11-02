package cs.ubbcluj.ro.cleannotes.event;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 02.11.2015.
 */
public class NotePressedEvent {

    private final Long id;
    public final Note note;

    public NotePressedEvent(Long id) {
        this.id = id;
        this.note = Note.findById(Note.class, id);
    }
}
