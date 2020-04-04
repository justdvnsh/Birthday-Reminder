package com.divyansh.birthdayreminder.ui;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.divyansh.birthdayreminder.data.Entities.Event;
import com.divyansh.birthdayreminder.data.EventDatabase;

import java.time.LocalDateTime;
import java.util.List;

public class BirthdayViewModel extends AndroidViewModel {

    private final LiveData<List<Event>> birthdayList;

    private EventDatabase db;

    public BirthdayViewModel(@NonNull Application application) {
        super(application);

        db = EventDatabase.getInstance(this.getApplication());

        birthdayList = db.eventDAO().getEvents(LocalDateTime.now());
    }

    public LiveData<List<Event>> getAllEvents(){
        return birthdayList;
    }

    public void deleteBirthday(Event event){
        new deleteAsyncTask(db).execute(event);
    }

    private static class deleteAsyncTask extends AsyncTask<Event, Void, Void> {

        private EventDatabase db;

        deleteAsyncTask(EventDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Event... events) {
            db.eventDAO().delete(events[0]);
            return null;
        }
    }
}
