package cs.ubbcluj.ro.cleannotes.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import cs.ubbcluj.ro.cleannotes.Constants;
import cs.ubbcluj.ro.cleannotes.MainActivity;
import cs.ubbcluj.ro.cleannotes.R;
import cs.ubbcluj.ro.cleannotes.model.BaseActivity;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.model.fragment.BaseFragment;
import cs.ubbcluj.ro.cleannotes.util.KeyboardUtils;

public class DetailFragment extends BaseFragment {

    MainActivity mMainActivity;

    @Bind(R.id.content_fd)
    EditText mContentEt;

    @Bind(R.id.title_fd)
    EditText mTitleEt;
    private Note mTargetedNote;


    public DetailFragment() { }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KeyboardUtils.showKeyboard(mContentEt);

        final Bundle args = getArguments();
        if (args != null) {
            Note note = (Note) args.getSerializable(Constants.KEY_NOTE);
            this.setTargetedNote(note);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainActivity = (MainActivity) getActivity();

        mMainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mMainActivity.setOnBackStackPoppedListener(new BaseActivity.OnBackStackPoppedListener() {
            @Override
            public void onPopped() {
                final String title = mTitleEt.getText().toString();
                final String content = mContentEt.getText().toString();

                if (!noteBeingEdited()) {
                    mTargetedNote = new Note();
                }

                mTargetedNote.setTitle(title);
                mTargetedNote.setContent(content);
                mTargetedNote.save();
            }
        });
    }

    /**
     * Tells if a note came from the list and it's being edited rather than a new one being created.
     */
    private boolean noteBeingEdited() {
        return this.mTargetedNote != null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    public static Fragment newInstance(Note note) {
        final DetailFragment fragment = new DetailFragment();
        if (note != null) {
            Bundle args = new Bundle();
            args.putSerializable(Constants.KEY_NOTE, note);
            fragment.setArguments(args);
        }
        return fragment;
    }



    public void setTargetedNote(Note targetedNote) {
        this.mTargetedNote = targetedNote;
        fillFields(targetedNote);
    }

    private void fillFields(Note targetedNote) {
        mTitleEt.setText(targetedNote.getTitle());
        mContentEt.setText(targetedNote.getContent());
    }


}
