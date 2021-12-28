package com.example.todoapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.todoapp.R;
import com.example.todoapp.data.Formatter;
import com.example.todoapp.models.Priority;
import com.example.todoapp.models.SharedViewModel;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskViewModel;
import com.google.android.material.chip.Chip;

import java.sql.Struct;
import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class TodoAddFragment extends Fragment implements View.OnClickListener {
    private EditText taskTitle;
    private EditText taskDetails;
    private RadioGroup priorityGroup;
    private CalendarView calendarView;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    Chip todayChip;
    Chip tomorrowChip;
    private Date endDate;
    private Calendar calendar = Calendar.getInstance();
    private ImageButton addTaskButton;
    private SharedViewModel sharedViewModel;
    private boolean isEdit;
    private Priority priority;
    private RadioButton priorityHigh;
    private RadioButton priorityMedium;
    private RadioButton priorityLow;


    public static TodoAddFragment newInstance(){
        TodoAddFragment fragment = new TodoAddFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //change the app bar title
        getActivity().setTitle("ADD TASK");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_add,container,false);
        taskTitle = view.findViewById(R.id.task_title_editText);
        taskDetails = view.findViewById(R.id.task_desc_editText);
        calendarView = view.findViewById(R.id.calendarView);
        addTaskButton = view.findViewById(R.id.add_task_button);
        todayChip  = view.findViewById(R.id.chip_today);
        tomorrowChip = view.findViewById(R.id.chip_tomorrow);
        priorityGroup = view.findViewById(R.id.priority_radio_group);
        priorityHigh = view.findViewById(R.id.priority_high_rb);
        priorityMedium = view.findViewById(R.id.priority_med_rb);
        priorityLow = view.findViewById(R.id.priority_low_rb);

        todayChip.setOnClickListener(this);
        tomorrowChip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getSelectItem().getValue()!=null){
            isEdit = sharedViewModel.getIsEdit();
            Task task = sharedViewModel.getSelectItem().getValue();
            taskTitle.setText(task.getTaskTitle());
            taskDetails.setText(task.getTaskDetails());
            priority = task.getPriority();
            if(priority == Priority.HIGH){
               priorityHigh.setChecked(true);
            }
            else if(priority == Priority.LOW){
                priorityLow.setChecked(true);
            }
            else{
                priorityMedium.setChecked(true);
            }
            endDate = task.getEndDate();
            calendar.setTime(endDate);
            calendarView.setDate(endDate.getTime());


        }
        else{
            sharedViewModel.setIsEdit(false);
            taskTitle.setText("");
            taskDetails.setText("");
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
                calendar.clear();
                calendar.set(year, month, dayOfMonth);
                endDate = calendar.getTime();
        });

        priorityGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            selectedButtonId = checkedId;
            selectedRadioButton= view.findViewById(selectedButtonId);
            if(selectedRadioButton.getId()==R.id.priority_high_rb){
                priority = Priority.HIGH;
            }
            else if(selectedRadioButton.getId()==R.id.priority_med_rb){
                priority = Priority.MEDIUM;
            }
            else if(selectedRadioButton.getId()==R.id.priority_low_rb){
                priority = Priority.LOW;
            }
            else{
                priority = Priority.LOW;
            }
        });

        addTaskButton.setOnClickListener(view1 -> {

            if(validation() & endDate!=null && priority!=null) {
                addTask();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id =  view.getId();
        if (id == R.id.chip_today){
            calendar.add(Calendar.DAY_OF_YEAR,0);
            endDate = calendar.getTime();
        }
        else if(id == R.id.chip_tomorrow){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            endDate = calendar.getTime();
            Log.d("DATE12", endDate.toString());
        }
    }

    private boolean validation(){
        String title = taskTitle.getText().toString().trim();
        String details = taskDetails.getText().toString().trim();

        if(title.isEmpty()){
            taskTitle.setError("Kindly Enter Task Title !");
            return false;
        }
        else if(details.isEmpty()){
            taskDetails.setError("Kindly Enter Task Description !");
            return false;
        }
        else if(priority==null){
            Toasty.warning(getContext(), "Kindly select the priority !", Toasty.LENGTH_SHORT,true).show();
            return false;
        }
        else if(endDate==null){
            Toasty.warning(getContext(), "Kindly select the end Date !", Toasty.LENGTH_SHORT,true).show();
            return false;
        }
        else{
            return true;
        }
    }

    private void addTask() {
        String title = taskTitle.getText().toString().trim();
        String details = taskDetails.getText().toString().trim();
        Task task = new Task(title,details, priority, endDate,Calendar.getInstance().getTime(),false);
        if(isEdit){
            Task updateTask = sharedViewModel.getSelectItem().getValue();
            updateTask.setTaskTitle(title);
            updateTask.setTaskDetails(details);
            updateTask.setEndDate(endDate);
            updateTask.setPriority(priority);
            updateTask.setStartDate(Calendar.getInstance().getTime());
            updateTask.setCompleted(false);
            TaskViewModel.update(updateTask);
            sharedViewModel.setIsEdit(false);


        }
        else {
            TaskViewModel.insert(task);
        }
        taskTitle.setText("");
        taskDetails.setText("");
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();
    }
}