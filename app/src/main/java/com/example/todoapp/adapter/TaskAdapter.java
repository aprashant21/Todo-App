package com.example.todoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.R;
import com.example.todoapp.data.Formatter;
import com.example.todoapp.models.Task;
import com.google.android.material.chip.Chip;

import java.text.Normalizer;
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
        View view = inflater.inflate(R.layout.item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = todolist.get(position);
        String day = Formatter.formatDay(task.getEndDate());
        holder.day.setText(day);
        String month = Formatter.formatMonth(task.getEndDate());
        holder.month.setText(month);
        String date = Formatter.formatDate(task.getEndDate());
        holder.date.setText(date);
        holder.taskTitle.setText(task.getTaskTitle());
        holder.taskDetails.setText(task.getTaskDetails());
        holder.status.setText(task.isCompleted()==false?"NOT COMPLETED":"COMPLETED");
        holder.radioButton.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatRadioButton radioButton;
        public AppCompatTextView taskTitle,taskDetails,day,date, month,status;
        TodoClickListener onTodoClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle=itemView.findViewById(R.id.title);
            taskDetails = itemView.findViewById(R.id.description);
            radioButton = itemView.findViewById(R.id.todo_radio_button2);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.month);
            status = itemView.findViewById(R.id.status);
            this.onTodoClickListener = todoClickListener;
            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            Task currentTask = todolist.get(getAdapterPosition());
            if(id==R.id.cardViewItem){
                onTodoClickListener.toDoClick(currentTask);
            }
            else if(id==R.id.todo_radio_button2) {
                onTodoClickListener.toDoRadioButtonClick(currentTask);
            }
        }
    }
    public interface TodoClickListener{
        void toDoClick(Task task);
        void toDoRadioButtonClick(Task task);
    }
}
