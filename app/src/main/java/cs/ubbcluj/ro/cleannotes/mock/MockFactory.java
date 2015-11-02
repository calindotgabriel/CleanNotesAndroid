package cs.ubbcluj.ro.cleannotes.mock;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 20.10.2015.
 */
public class MockFactory {

    public static final String CONTENT = "Breakfast at Tiffany's";

    public static Note createFakeNote() {
        return new Note(CONTENT);
    }
}
