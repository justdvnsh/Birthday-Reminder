package com.divyansh.birthdayreminder.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.divyansh.birthdayreminder.R;
import com.divyansh.birthdayreminder.data.Entities.Event;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Event> birthdayList;
    private mOnItemClickListener listener;

    public interface mOnItemClickListener {
        void onItemClick(Event event);
    }

    public RecyclerViewAdapter(List<Event> birthdayList, mOnItemClickListener listener) {
        this.birthdayList = birthdayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Event event = birthdayList.get(position);
        holder.postedOn.setText(event.getDate().toString());
        holder.title.setText(event.getName());
        holder.description.setText(event.getDescription());
        holder.daysRemaning.setText(String.valueOf(event.getDaysUntil()));
    }

    @Override
    public int getItemCount() {
        if (birthdayList.size() == 0) return 0;
        return birthdayList.size();
    }

    public void addBithdays(List<Event> birthdayList) {
        this.birthdayList = birthdayList;
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView postedOn;
        TextView title;
        TextView description;
        TextView daysRemaning;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            postedOn = itemView.findViewById(R.id.publish_date);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.content);
            daysRemaning = itemView.findViewById(R.id.time_left);
        }

        @Override
        public void onClick(View v) {

        }
    }
}