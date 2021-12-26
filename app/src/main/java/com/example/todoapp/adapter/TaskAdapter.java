package com.example.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.data.Formatter;
import com.example.todoapp.models.Task;
import com.google.android.material.chip.Chip;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    List<Task> todolist;

    public TaskAdapter(List<Task> todolist) {
        this.todolist = todolist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = todolist.get(position);
        String dateFormatter= Formatter.formatDate(task.getEndDate());
        holder.taskTitle.setText(task.getTaskTitle());
        holder.todayChip.setText(dateFormatter);
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatRadioButton radioButton;
        public AppCompatTextView taskTitle;
        public Chip todayChip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.todo_radio_button);
            taskTitle=itemView.findViewById(R.id.todo_item_task_title);
            todayChip=itemView.findViewById(R.id.todo_item_chip);

        }
    }
}
