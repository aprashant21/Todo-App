package com.example.todoapp.data;

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
}
