package com.example.todoapp.data;

import android.graphics.Color;

import com.example.todoapp.models.Priority;
import com.example.todoapp.models.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Formatter {
    public static String formatDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String dayDate = cal.get(Calendar.DAY_OF_MONTH)+"";
        return dayDate;
    }
    public static String formatMonth(Date date){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("MMM");
        return simpleDateFormat.format(date);
    }
    public static String formatDay(Date date){
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE");
        return simpleDateFormat.format(date);
    }

    public static int priorityColor(Task task) {
        int color;
        if(task.getPriority() == Priority.HIGH){
            color = Color.argb(200, 201, 21,23);
        }
        else if(task.getPriority()== Priority.MEDIUM){
            color = Color.argb(200, 155, 179,0);
        }
        else {
            color = Color.argb(200, 51, 181,129);
        }
        return color;
    }
}
