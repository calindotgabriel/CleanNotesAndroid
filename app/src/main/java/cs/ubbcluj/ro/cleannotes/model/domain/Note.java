package cs.ubbcluj.ro.cleannotes.model.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Model class used for notes.
 */
public class Note extends RealmObject {

    @PrimaryKey
    private int id;

    private String title;
    private String content;

    public Note() {
    }

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

    public Note(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
