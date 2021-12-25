package com.example.todoapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;

import com.example.todoapp.data.Converter;
import com.example.todoapp.data.TaskDao;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.todoapp.models.Task;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
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
                        TaskDao taskDao = sInstance.taskDao();
                        taskDao.deleteAll(); //it cleans state




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
