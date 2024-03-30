package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.TaskAdapter;
import com.example.myapplication.Model.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

//5edmet el widget BottomDialog
public class AddNewTask extends BottomSheetDialogFragment {
    private boolean finalIsUpdate = false;
    public static final String TAG = "ActionBottomDialog";
    private EditText newTaskText;
    private Button newTaskSaveButton;

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;


    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_task, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        newTaskText = view.findViewById(R.id.newTaskText);
       newTaskSaveButton = view.findViewById(R.id.newTaskButton);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        taskAdapter = new TaskAdapter((MainActivity) getActivity(), (MainActivity) getActivity()); //adapter call
        setupTextWatcher(newTaskText);
        setupSaveButton(newTaskSaveButton);
        return view;
    }


    private void setupTextWatcher(EditText newEditText) { //on changed on flutter framework
        if (newTaskText != null) {
            newTaskText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    updateSaveButtonState(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    private void setupSaveButton(Button newTaskSaveButton) {
        this.newTaskSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClick", "Save Button Clicked");
                String text = newTaskText.getText().toString();
                if (finalIsUpdate) {
                    Log.d("ButtonClick", "Update Clicked");
                } else {
                    Log.d("ButtonClick", "Add New Task Clicked");
                    // Handle adding new task with dummy data
                     TaskModel task = new TaskModel(text, "",false);
                   // taskAdapter.setTasks(Collections.singletonList(task));  //
                    if (taskAdapter != null) {
                        taskAdapter.addItem(task);
                    }
                    List<TaskModel> tasks = taskAdapter.getTasks();
                    Log.d("TaskList", "Task List Contents:");
                    for (TaskModel taskModel : tasks) {
                        Log.d("TaskList", taskModel.toString());
                    }
                }
                dismiss();
            }
        });
    }

    private void updateSaveButtonState(String inputText) {
        if (inputText.equals("")) {
            newTaskSaveButton.setEnabled(false);
            newTaskSaveButton.setTextColor(Color.GRAY);
        } else {
            newTaskSaveButton.setEnabled(true);
            newTaskSaveButton.setTextColor(ContextCompat.getColor(
                    requireContext(), R.color.colorPrimaryDark));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        taskAdapter = new TaskAdapter((MainActivity) getActivity(), (MainActivity) getActivity());//instanciation
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(taskAdapter);
    }

}

