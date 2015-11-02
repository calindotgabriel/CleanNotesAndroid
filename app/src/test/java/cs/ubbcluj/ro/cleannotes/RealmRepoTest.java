package cs.ubbcluj.ro.cleannotes;

import android.app.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.repo.Repo;

import static org.junit.Assert.*;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RealmRepoTest {

    private Application mContext;

    public RealmRepoTest() {
        mContext = RuntimeEnvironment.application;
    }

    @Test
    public void addNote() throws Exception {
        Repo<Note> repo = new NoteRealmRepo(mContext);
        Note addedNote = repo.add(MockFactory.createFakeNote());
        assertEquals(addedNote.getContent(), MockFactory.CONTENT);
    }
}