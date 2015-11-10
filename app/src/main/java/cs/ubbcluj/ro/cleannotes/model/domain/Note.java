package cs.ubbcluj.ro.cleannotes.model.domain;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model class used for notes.
 */
public class Note extends SugarRecord<Note> implements Serializable {

    private String title;
    private String content;

    public Note() {
    } //required.

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        return Objects.equals(this.getId(), note.getId());
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
