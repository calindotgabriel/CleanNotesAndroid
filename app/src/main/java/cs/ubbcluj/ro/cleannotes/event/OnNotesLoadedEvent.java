package cs.ubbcluj.ro.cleannotes.event;

import java.util.List;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 02.11.2015.
 */
public class OnNotesLoadedEvent {

    public final List<Note> notes;

    public OnNotesLoadedEvent(List<Note> notes) {
        this.notes = notes;
    }

}
