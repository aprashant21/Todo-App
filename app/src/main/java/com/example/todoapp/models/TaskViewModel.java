package com.example.todoapp.models;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.todoapp.data.Repository;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static Repository repository;
    public final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTasks = repository.getAllTask();
    }
    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }
    public static void insert(Task task){
        repository.insert(task);
    }
    public LiveData<Task> get(long id){
        return repository.get(id);
    }
    public static void update(Task task){
        repository.updateTask(task);
    }
    public static void delete(Task task){
        repository.deleteTask(task);
    }

}
