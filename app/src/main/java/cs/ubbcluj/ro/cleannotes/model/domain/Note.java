package cs.ubbcluj.ro.cleannotes.model.domain;

import com.orm.SugarRecord;

/**
 * Model class used for notes.
 */
public class Note extends SugarRecord<Note> {

    private String title;
    private String content;

    public Note() { } //required.

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
