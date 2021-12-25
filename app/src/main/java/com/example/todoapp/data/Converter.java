package com.example.todoapp.data;

import androidx.room.TypeConverter;

import com.example.todoapp.models.Priority;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date fromTimeStamp(Long timeStamp){
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromPriority(Priority priority){
        return priority==null ? null: priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String priority){
        return priority==null ? null: Priority.valueOf(priority);
    }
}
