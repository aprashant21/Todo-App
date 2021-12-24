package com.example.todoapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.database.TaskDatabase;
import com.example.todoapp.models.Task;

import java.util.List;

public class Repository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> todos;

    private TaskDatabase database;

    public Repository(Application application) {
        database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        todos = taskDao.getAllTask();
    }

    public LiveData<List<Task>> getAllTask(){
        return todos;
    }

    public void insert(Task task){
        TaskDatabase.databaseWriteExecutor.execute(()->taskDao.insertTask(task));
    }

    public LiveData<Task> get(long id){return taskDao.get(id);}

    public void updateTask(Task task){
        TaskDatabase.databaseWriteExecutor.execute(()->taskDao.updateTask(task));
    }

    public void deleteTask(Task task){
        TaskDatabase.databaseWriteExecutor.execute(()->taskDao.delete(task));
    }
}
