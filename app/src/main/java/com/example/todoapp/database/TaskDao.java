package com.example.todoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Query("SELECT * FROM table_task")
    LiveData<List<Task>> getTask();

    @Query("SELECT * FROM table_task where table_task.task_id == :id")
    LiveData<Task> get(long id);

    @Update
    void updateTask(Task task);

    @Query("DELETE FROM table_task")
    void deleteAll();

    @Delete
    void delete(Task task);
}
