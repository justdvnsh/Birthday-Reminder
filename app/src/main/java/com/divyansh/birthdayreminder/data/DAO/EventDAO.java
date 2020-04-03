package com.divyansh.birthdayreminder.data.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.divyansh.birthdayreminder.data.Entities.Event;

import java.time.LocalDateTime;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EventDAO {

    @Query("SELECT * FROM  " + Event.TABLE_NAME + " WHERE " + Event.DATE_FIELD + " > :minDate")
    LiveData<List<Event>> getEvents(LocalDateTime minDate);

    @Insert(onConflict = REPLACE)
    void addEvent(Event event);

    @Delete
    void delete(Event event);

    @Update(onConflict = REPLACE)
    void update(Event event);
}
