package com.divyansh.birthdayreminder.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.divyansh.birthdayreminder.R;
import com.divyansh.birthdayreminder.adapters.RecyclerViewAdapter;
import com.divyansh.birthdayreminder.data.Entities.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.mOnItemClickListener {

    private BirthdayViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private FloatingActionButton fab;

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
        fab = findViewById(R.id.fab);

        viewModel = ViewModelProviders.of(this).get(BirthdayViewModel.class);
        viewModel.getAllEvents().observe(MainActivity.this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                adapter.addBithdays(events);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddBirthdayActivity.class));
            }
        });
    }

    @Override
    public void onItemClick(Event event) {

    }
}
