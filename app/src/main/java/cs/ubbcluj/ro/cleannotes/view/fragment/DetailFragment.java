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
import cs.ubbcluj.ro.cleannotes.model.BaseActivity;
import cs.ubbcluj.ro.cleannotes.util.KeyboardUtils;
import de.greenrobot.event.EventBus;

public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MainActivity mMainActivity;

    @Bind(R.id.content_fd)
    EditText mContentEt;

    @Bind(R.id.title_fd)
    EditText mTitleEt;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KeyboardUtils.showKeyboard(mContentEt);
    }

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
                    EventBus.getDefault().postSticky(new NoteCreatedEvent(title,  content));
                } else {
                    Toast.makeText(mMainActivity, "Empty note not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
