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
import cs.ubbcluj.ro.cleannotes.model.domain.Note;

/**
 * Created by motan on 20.10.2015.
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final Context mContext;

    public interface EventCallback {
        void onNoteDeleted(int note);
    }

    private List<Note> mNotes;
    private EventCallback mEventCallback;

    public void setEventCallback(EventCallback mEventCallback) {
        this.mEventCallback = mEventCallback;
    }

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



    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.note_content_tv)
        TextView mContent;
        @Bind(R.id.note_title_tv)
        TextView mTitle;


        @OnClick(R.id.note_item_delete)
        void onDeletePressed() {
            Note note = mNotes.get(getAdapterPosition());
            reportNoteDeleted(note.getId());
            notifyDataSetChanged();
        }

        @OnClick(R.id.note_item_share)
        void onSharePressed() {
            Note note = mNotes.get(getAdapterPosition());
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, note.getTitle());
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, note.getContent());
            mContext.startActivity(Intent.createChooser(sharingIntent, "Share CleanNote"));
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void reportNoteDeleted(int noteId) {
        if (mEventCallback != null) {
            mEventCallback.onNoteDeleted(noteId);
        }
    }

}
