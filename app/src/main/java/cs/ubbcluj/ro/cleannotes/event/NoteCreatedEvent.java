package cs.ubbcluj.ro.cleannotes.event;

/**
 * Created by motan on 02.11.2015.
 */
public class NoteCreatedEvent {

    private final String title;
    private final String content;

    public NoteCreatedEvent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
