package cs.ubbcluj.ro.cleannotes;

import android.test.ActivityInstrumentationTestCase2;

import java.util.List;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.manager.NoteManager;
import cs.ubbcluj.ro.cleannotes.mock.MockFactory;
import cs.ubbcluj.ro.cleannotes.repo.Repo;

/**
 * Created by motan on 20.10.2015.
 */
public class ManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public ManagerTest() {
        super(MainActivity.class);
    }
/*

    public void testAddThenDelete() {
        final Repo<Note, Integer> repo = new NoteRealmRepo(getActivity());
        repo.clear();

        Note note = MockFactory.createFakeNote();

        //ADD
        NoteManager manager = new NoteManager(getActivity());
        manager.add(note);

        //CHECK ADDING CORRECT
        final List<Note> notesReference = manager.getNotesReference();
        assertSize(repo, notesReference, 1);


        manager.delete(note.getId());
        assertSize(repo, notesReference, 0);
    }


    private void assertSize(Repo<Note> repo, List<Note> notesReference, int size) {
        assertEquals(notesReference.size(), size);
        assertEquals(repo.findAll().size(), size);
    }
*/

}
