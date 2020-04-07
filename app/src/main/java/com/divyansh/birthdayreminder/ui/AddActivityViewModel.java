package com.divyansh.birthdayreminder.ui;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.divyansh.birthdayreminder.data.Entities.Event;
import com.divyansh.birthdayreminder.data.EventDatabase;

public class AddActivityViewModel extends AndroidViewModel {

    private EventDatabase db;

    public AddActivityViewModel(@NonNull Application application) {
        super(application);

        db = EventDatabase.getInstance(this.getApplication());
    }

    public void insertBirthday(Event event) {
        new insertBirthdayAsyncTask(db).execute(event);
    }

    private static class insertBirthdayAsyncTask extends AsyncTask<Event, Void, Void> {

        private EventDatabase db;

        insertBirthdayAsyncTask(EventDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(Event... events) {
            db.eventDAO().addEvent(events[0]);
            return null;
        }
    }
}
