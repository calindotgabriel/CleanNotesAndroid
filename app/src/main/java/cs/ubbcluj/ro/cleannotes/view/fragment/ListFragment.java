package cs.ubbcluj.ro.cleannotes.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cs.ubbcluj.ro.cleannotes.MainActivity;
import cs.ubbcluj.ro.cleannotes.R;
import cs.ubbcluj.ro.cleannotes.async.LoadNotesTask;
import cs.ubbcluj.ro.cleannotes.event.OnNotePressedEvent;
import cs.ubbcluj.ro.cleannotes.event.OnNotesLoadedEvent;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import cs.ubbcluj.ro.cleannotes.model.fragment.StickyBusFragment;
import cs.ubbcluj.ro.cleannotes.view.RecyclerViewEmptySupport;
import cs.ubbcluj.ro.cleannotes.view.adapter.NoteAdapter;

/**
 * Created by motan on 30.10.2015.
 */
public class ListFragment extends StickyBusFragment {

    private final String TAG = this.getClass().getCanonicalName();

    @Bind(R.id.rv_fl)
    RecyclerViewEmptySupport mRecyclerView;

    @Bind(R.id.ev_fl)
    TextView mEmptyView;


    /**
     * Called when the notes finished querying from the DB.
     * @param event contains notes.
     */
    public void onEvent(OnNotesLoadedEvent event) {
        initList(event.notes);
    }


    public void onEvent(OnNotePressedEvent event) {
        switchToDetailFragment(event.note);
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadNotesTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void initList(List<Note> notes) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        NoteAdapter adapter = new NoteAdapter(getActivity(), notes);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setEmptyView(mEmptyView);
    }


    @OnClick(R.id.fab)
    void onFabClicked() {
        switchToDetailFragment(null);
    }

    private void switchToDetailFragment(Note note) {
        getActivity().getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right,
                        R.anim.enter_from_left, R.anim.exit_to_left)
                .replace(R.id.fragment_container, DetailFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }

}
