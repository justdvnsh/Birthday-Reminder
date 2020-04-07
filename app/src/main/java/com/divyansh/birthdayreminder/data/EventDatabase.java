package com.divyansh.birthdayreminder.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.divyansh.birthdayreminder.data.DAO.EventDAO;
import com.divyansh.birthdayreminder.data.Entities.Event;

@Database(entities = {Event.class}, version = 2)
@TypeConverters(DateTypeConverter.class)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase INSTANCE;

    public static EventDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class, "events")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract EventDAO eventDAO();
}
