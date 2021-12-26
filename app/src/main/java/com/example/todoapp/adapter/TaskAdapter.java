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
    private final TodoClickListener todoClickListener;

    public TaskAdapter(List<Task> todolist, TodoClickListener todoClickListener) {
        this.todolist = todolist;
        this.todoClickListener = todoClickListener ;
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
        holder.radioButton.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatRadioButton radioButton;
        public AppCompatTextView taskTitle;
        public Chip todayChip;
        TodoClickListener onTodoClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.todo_radio_button);
            taskTitle=itemView.findViewById(R.id.todo_item_task_title);
            todayChip=itemView.findViewById(R.id.todo_item_chip);
            this.onTodoClickListener = todoClickListener;
            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            Task currentTask = todolist.get(getAdapterPosition());
            if(id==R.id.todo_item_layout){
                onTodoClickListener.toDoClick(getAdapterPosition(),currentTask);
            }
            else if(id==R.id.todo_radio_button) {
                onTodoClickListener.toDoRadioButtonClick(currentTask);
            }
        }
    }
    public interface TodoClickListener{
        void toDoClick(int adapterPosition, Task task);
        void toDoRadioButtonClick(Task task);
    }
}
