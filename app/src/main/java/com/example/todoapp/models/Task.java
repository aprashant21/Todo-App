package com.example.todoapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "table_task")
public class Task {
    @ColumnInfo(name="task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskId;
    @ColumnInfo(name="task_title")
    public String taskTitle;
    @ColumnInfo(name="task_details")
    public String taskDetails;
    public Priority priority;
    @ColumnInfo(name = "end_date")
    public Date endDate;
    @ColumnInfo(name = "start_date")
    public Date startDate;
    @ColumnInfo(name="is_complete")
    public boolean isCompleted;

    public Task(String taskTitle, String taskDetails, Priority priority, Date endDate, Date startDate, boolean isCompleted) {
        this.taskTitle = taskTitle;
        this.taskDetails = taskDetails;
        this.priority = priority;
        this.endDate = endDate;
        this.startDate = startDate;
        this.isCompleted = isCompleted;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                ", priority=" + priority +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
