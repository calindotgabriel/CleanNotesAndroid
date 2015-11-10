package cs.ubbcluj.ro.cleannotes.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cs.ubbcluj.ro.cleannotes.R;
import cs.ubbcluj.ro.cleannotes.event.OnNotePressedEvent;
import cs.ubbcluj.ro.cleannotes.model.domain.Note;
import de.greenrobot.event.EventBus;

/**
 * Created by motan on 20.10.2015.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final String TAG = this.getClass().getCanonicalName();
    private final Context mContext;
    private List<Note> mNotes;


    public NoteAdapter(Context context, List<Note> notes) {
        this.mNotes = notes;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.note_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mContent.setText(mNotes.get(position).getContent());
        holder.mTitle.setText(mNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    private void deleteNote(int adapterPosition) {
        Note note = mNotes.get(adapterPosition);

        mNotes.remove(note);
        note.delete();

        notifyItemRemoved(adapterPosition);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_content_tv)
        TextView mContent;
        @Bind(R.id.note_title_tv)
        TextView mTitle;

        @OnClick(R.id.container_nrvi)
        void onNotePresed() {
            EventBus.getDefault().post(new OnNotePressedEvent(getNoteId()));
        }

        @OnClick(R.id.note_item_delete)
        void onDeletePressed()
        {
            deleteNote(getAdapterPosition());
        }


        @OnClick(R.id.note_item_share)
        void onSharePressed() {
            startSharingIntent();
        }

        private void startSharingIntent() {
            Note note = mNotes.get(getAdapterPosition());
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, note.getTitle());
            sharingIntent.putExtra(Intent.EXTRA_TEXT, note.getContent());
            mContext.startActivity(Intent.createChooser(sharingIntent, mContext.getString(R.string.share_note)));
        }


        /**
         * Given a position inside the adapter, returns the Id of the note.
         */
        private Long getNoteId() {
            return mNotes.get(getAdapterPosition()).getId();
        }


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
