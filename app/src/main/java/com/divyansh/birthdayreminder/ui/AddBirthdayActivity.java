package com.divyansh.birthdayreminder.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.divyansh.birthdayreminder.R;
import com.divyansh.birthdayreminder.data.Entities.Event;

import java.time.LocalDateTime;

public class AddBirthdayActivity extends AppCompatActivity {

    private EditText name;
    private EditText description;
    private DatePicker date;
    private TimePicker time;
    private Button submit;
    private AddActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_birthday);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        date = findViewById(R.id.datePicker);
        time = findViewById(R.id.timePicker);
        submit = findViewById(R.id.submit);
        viewModel = ViewModelProviders.of(this).get(AddActivityViewModel.class);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime localDateTime = LocalDateTime.of(date.getYear(), date.getMonth() + 1, date.getDayOfMonth(), time.getHour(), time.getMinute());
                Log.i("TIME AND DATE ->", localDateTime.toString());
                Event event = new Event(name.getText().toString(), description.getText().toString(), LocalDateTime.now());
                viewModel.insertBirthday(event);
                finish();
            }
        });
    }
}
