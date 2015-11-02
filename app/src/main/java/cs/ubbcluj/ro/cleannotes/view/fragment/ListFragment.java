package cs.ubbcluj.ro.cleannotes.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cs.ubbcluj.ro.cleannotes.R;
import cs.ubbcluj.ro.cleannotes.event.NoteCreatedEvent;
import cs.ubbcluj.ro.cleannotes.manager.NoteManager;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.view.RecyclerViewEmptySupport;
import cs.ubbcluj.ro.cleannotes.view.adapter.NoteAdapter;
import cs.ubbcluj.ro.cleannotes.view.fragment.DetailFragment;
import de.greenrobot.event.EventBus;

/**
 * Created by motan on 30.10.2015.
 */
public class ListFragment extends Fragment {

    @Bind(R.id.rv_fl)
    RecyclerViewEmptySupport mRecyclerView;

    @Bind(R.id.ev_fl)
    TextView mEmptyView;

    private NoteManager mManager;
    private NoteAdapter mAdapter;

    @Override
    public void onStart() {
        EventBus.getDefault().registerSticky(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    /**
     * Called when a new note was added.
     * @param event contains information.
     */
    public void onEvent(NoteCreatedEvent event) {
        final Note note = new Note(event.getTitle(), event.getContent());
        mManager.add(note);
        mAdapter.notifyDataSetChanged();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
    }

    private void initUI() {
        mManager = new NoteManager(getActivity());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new NoteAdapter(getActivity(), mManager.getNotesReference());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setEmptyView(mEmptyView);
        mAdapter.setEventCallback(new NoteAdapter.EventCallback() {
            @Override
            public void onNoteDeleted(int noteId) {
                mManager.delete(noteId);
            }
        });
    }


    @OnClick(R.id.fab)
    void onFabClicked() {
        goToAddFragment();
    }

    private void goToAddFragment() {
        final DetailFragment fragment = DetailFragment.newInstance("1", "2");

        getActivity().getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,
                        R.anim.enter_from_left, R.anim.exit_to_left)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
