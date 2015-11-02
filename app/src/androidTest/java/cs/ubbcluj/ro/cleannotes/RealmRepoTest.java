package cs.ubbcluj.ro.cleannotes;

import android.test.ActivityInstrumentationTestCase2;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.mock.MockFactory;
import cs.ubbcluj.ro.cleannotes.repo.CRUDRepo;
import cs.ubbcluj.ro.cleannotes.repo.NoteRealmRepo;
import cs.ubbcluj.ro.cleannotes.repo.RealmUtils;

/**
 * Created by motan on 20.10.2015.
 */
public class RealmRepoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private CRUDRepo<Note> mRepo;

    public RealmRepoTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        if (mRepo == null) {
            mRepo = new NoteRealmRepo(getActivity());
        }
        RealmUtils.cleanupTable(getActivity(), Note.class);
    }


    /**
     * Tests creating and saving a object into the Realm Repo.
     */
    public void testCreate() {
        Note note = mRepo.add(MockFactory.createFakeNote());
        assertEquals(note.getContent(), MockFactory.CONTENT);
    }

    /**
     * Tests adding more than 1 object to repo.
     */
    public void testAddMoreObjects() {
        Note note1 = mRepo.add(MockFactory.createFakeNote());
        Note note2 = mRepo.add(MockFactory.createFakeNote());
        Note note3 = mRepo.add(MockFactory.createFakeNote());
        assertEquals(mRepo.size(), 3);
    }

    /**
     * Tests adding and then finding object.
     */
    public void testFind() {
        Note insertedNote = mRepo.add(MockFactory.createFakeNote());
        Note foundNote = mRepo.find(insertedNote.getId());
        assertEquals(insertedNote, foundNote);
    }

    /**
     * Tests deleting a note.
     */
    public void testDelete() {
        Note insertedNote = mRepo.add(MockFactory.createFakeNote());
        mRepo.delete(insertedNote.getId());
        assertTrue(mRepo.size() == 0);
    }

}
