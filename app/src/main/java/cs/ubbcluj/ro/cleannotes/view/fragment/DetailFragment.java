package cs.ubbcluj.ro.cleannotes.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cs.ubbcluj.ro.cleannotes.MainActivity;
import cs.ubbcluj.ro.cleannotes.R;
import cs.ubbcluj.ro.cleannotes.event.NoteCreatedEvent;
import cs.ubbcluj.ro.cleannotes.event.NotePressedEvent;
import cs.ubbcluj.ro.cleannotes.model.BaseActivity;
import cs.ubbcluj.ro.cleannotes.model.BusFragment;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.util.KeyboardUtils;
import de.greenrobot.event.EventBus;

public class DetailFragment extends BusFragment {

    MainActivity mMainActivity;

    @Bind(R.id.content_fd)
    EditText mContentEt;

    @Bind(R.id.title_fd)
    EditText mTitleEt;


    public DetailFragment() {
        // Required empty public constructor
    }


    public void onEvent(NotePressedEvent event) {
        //TODO should I not do this here ? research
        mTitleEt.setText(event.note.getTitle());
        mContentEt.setText(event.note.getContent());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KeyboardUtils.showKeyboard(mContentEt);
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
                //TODO better this logic.
                final String title = mTitleEt.getText().toString();
                final String content = mContentEt.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    EventBus.getDefault().postSticky(new NoteCreatedEvent(title, content));
                } else {
                    Toast.makeText(mMainActivity, "Empty note not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
