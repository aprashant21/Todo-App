package com.example.todoapp.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.todoapp.R;
import com.example.todoapp.models.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class TodoAddFragment extends BottomSheetDialogFragment {
    public TextView text;
    private TaskViewModel viewModel;
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Task");
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    }
}