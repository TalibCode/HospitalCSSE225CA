package com.example.hospital.RoomData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;

public class AppointmentAdapter extends ListAdapter<AppointmentTable, AppointmentAdapter.NoteHolder> {
    private static final String TAG = "MYNoteAdapter";
    private onItemClickListener listener;

    public AppointmentAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<AppointmentTable> DIFF_CALLBACK = new DiffUtil.ItemCallback<AppointmentTable>() {
        @Override
        public boolean areItemsTheSame(@NonNull AppointmentTable oldItem, @NonNull AppointmentTable newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @Override
        public boolean areContentsTheSame(@NonNull AppointmentTable oldItem, @NonNull AppointmentTable newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "MYonCreateViewHolder: inflator");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        AppointmentTable currentAppointmentTable = getItem(position);
        holder.textViewTitle.setText(currentAppointmentTable.getTitle());
        holder.textViewDescription.setText(currentAppointmentTable.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentAppointmentTable.getPriority()));
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "MYNoteHolder";
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;


        public NoteHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "NoteHolder: finding view");
            textViewTitle = itemView.findViewById(R.id.text_vie_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }


    public AppointmentTable getNoteAt(int position) {
        return getItem(position);
    }

    public interface onItemClickListener {
        void onItemClick(AppointmentTable appointmentTable);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

}
