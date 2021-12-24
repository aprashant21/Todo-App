package com.example.todoapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoapp.data.TodoDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class TaskDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS=4;
    public static final String DATABASE_NAME = "todo_db";
    private static TaskDatabase sInstance;
    private static final Object LOCK = new Object();
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(()->{
                        //we can invoke DAO and write
                    });
                }
            };
    public static TaskDatabase getInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TaskDatabase.class, TaskDatabase.DATABASE_NAME)
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
        }

        return sInstance;
    }

    public abstract TaskDao taskDao();

}
