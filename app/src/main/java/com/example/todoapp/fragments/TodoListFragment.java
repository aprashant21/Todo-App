package com.example.todoapp.fragments;

import static com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype.Fall;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoapp.R;
import com.example.todoapp.adapter.TaskAdapter;
import com.example.todoapp.models.Task;
import com.example.todoapp.models.TaskViewModel;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoListFragment extends Fragment implements TaskAdapter.TodoClickListener {
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
        getActivity().setTitle("TASK LIST");
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
                adapter = new TaskAdapter(tasks,TodoListFragment.this);
                if(tasks!=null) {
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void toDoClick(int adapterPosition, Task task) {
        Log.d("CLICK","onCLick"+task.startDate);
    }

    @Override
    public void toDoRadioButtonClick(Task task) {
        NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(getContext());
        dialogBuilder
                .withTitle("Are You Sure ?")
                .withIcon(R.drawable.ic_error)
                .withDuration(400)
                .isCancelableOnTouchOutside(false)
                .withMessage(R.string.delete_message)
                .withEffect(Fall)
                .withButton1Text("COMPLETED")
                .withButton2Text("CANCEL")
                .setButton1Click(view -> {
                    TaskViewModel.delete(task);
                    task.setCompleted(true);
                    dialogBuilder.dismiss();
                    adapter.notifyDataSetChanged();
                })
                .setButton2Click(view -> {
                    dialogBuilder.dismiss();
                    adapter.notifyDataSetChanged();
                }).show();
    }
}