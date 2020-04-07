package com.divyansh.birthdayreminder.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.divyansh.birthdayreminder.R;
import com.divyansh.birthdayreminder.adapters.RecyclerViewAdapter;
import com.divyansh.birthdayreminder.data.Entities.Event;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.mOnItemClickListener {

    private BirthdayViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(new ArrayList<Event>(), this);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(BirthdayViewModel.class);
        viewModel.getAllEvents().observe(MainActivity.this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.addBithdays(events);
            }
        });
    }

    @Override
    public void onItemClick(Event event) {

    }
}
