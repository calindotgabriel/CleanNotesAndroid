package cs.ubbcluj.ro.cleannotes.event;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 02.11.2015.
 */
public class NoteCreatedEvent {

    public final String title;
    public final String content;
    public final Note note;

    public NoteCreatedEvent(String title, String content) {
        this.title = title;
        this.content = content;
        this.note = new Note(title, content);
        this.note.save();
    }

}
