package com.example.todoapp.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private boolean isEdit;
    private final MutableLiveData<Task> selectedItem = new MutableLiveData<>();

    public LiveData<Task> getSelectItem() {
        return selectedItem;
    }

    public void selectItem(Task task){
        selectedItem.setValue(task);
    }
    public void setIsEdit(boolean isEdit){
        this.isEdit = isEdit;
    }
    public boolean getIsEdit(){
        return isEdit;
    }



}
