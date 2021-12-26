package com.example.todoapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.models.Priority;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import java.util.Calendar;
import java.util.Date;

public class TodoAddFragment extends Fragment implements View.OnClickListener {
    private EditText taskTitle;
    private EditText taskDetails;
    private RadioGroup priorityGroup;
    private TaskViewModel viewModel;
    private CalendarView calendarView;
    private RadioButton selectedRadioButton;
    private int selectedId;
    Chip todayChip;
    Chip tomorrowChip;
    private Date endDate;
    private Calendar calendar = Calendar.getInstance();
    private Button addTaskButton;


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_add,container,false);
        taskTitle = view.findViewById(R.id.task_title_editText);
        taskDetails = view.findViewById(R.id.task_desc_editText);
        calendarView = view.findViewById(R.id.calendarView);
        addTaskButton = view.findViewById(R.id.add_task_button);
        todayChip  = view.findViewById(R.id.chip_today);
        tomorrowChip = view.findViewById(R.id.chip_tomorrow);
        priorityGroup = view.findViewById(R.id.priority_radio_group);

        todayChip.setOnClickListener(this);
        tomorrowChip.setOnClickListener(this);




        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("ADD TASK");
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
                calendar.clear();
                calendar.set(year, month, dayOfMonth);
                endDate = calendar.getTime();
        });

        addTaskButton.setOnClickListener(view1 -> {

            if(validation() && endDate!=null) {
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
            Toast.makeText(getContext(), "Kindly Enter Task Title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(details.isEmpty()){
            Toast.makeText(getContext(), "Kindly Enter Task Description", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }

    }


    private void addTask() {
        String title = taskTitle.getText().toString().trim();
        String details = taskDetails.getText().toString().trim();
        Task task = new Task(title,details, Priority.HIGH, endDate,Calendar.getInstance().getTime(),false);
        TaskViewModel.insert(task);
        taskTitle.setText("");
        taskDetails.setText("");
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();
    }
}