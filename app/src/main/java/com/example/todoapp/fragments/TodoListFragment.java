package com.example.todoapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.adapter.TaskAdapter;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoListFragment extends Fragment {
    private FloatingActionButton fabButton;
    private RecyclerView recyclerView;
    private TaskViewModel viewModel;
    private TaskAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_todo_list, container, false);

        fabButton = view.findViewById(R.id.fab_button);
        recyclerView = view.findViewById(R.id.recyclerView_task);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Task List");
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(savedInstanceState==null) {
                    TodoAddFragment fragment = TodoAddFragment.newInstance();
                    FragmentManager fm = getParentFragmentManager();
                    fm.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                }
            }
        });

        viewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter = new TaskAdapter(tasks);
                if(tasks!=null) {
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}