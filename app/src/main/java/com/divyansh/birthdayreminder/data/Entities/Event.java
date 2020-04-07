package com.divyansh.birthdayreminder.data.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.divyansh.birthdayreminder.data.Entities.Event.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class Event {
    public static final String TABLE_NAME = "events";
    public static final String DATE_FIELD = "date";

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private String description;
    @ColumnInfo(name = DATE_FIELD)
    private LocalDateTime date;

    public Event(String name, String description, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

//    public int getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    public long getDaysUntil() {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), getDate());
    }
}
