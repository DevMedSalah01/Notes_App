package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.Model.TaskModel;
import com.example.myapplication.Adapter.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {



    private RecyclerView tasksRecyclerView;
    private TaskAdapter tasksAdapter;
    private List<TaskModel> taskList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Dummy data for tasks
        taskList = generateDummyTasks();

        tasksRecyclerView = findViewById(R.id.tasksRecycleView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new TaskAdapter(this, this); // Pass null for the DatabaseHandler since we're using dummy data
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("******************clicked******************");
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    private List<TaskModel> generateDummyTasks() {
        List<TaskModel> dummyTasks = new ArrayList<>();

        // Add some dummy tasks
        dummyTasks.add(new TaskModel("Task 1", "Description 1",false));
        dummyTasks.add(new TaskModel("Task 2", "Description 2",true));
        dummyTasks.add(new TaskModel("Task 3", "Description 3",false));
        dummyTasks.add(new TaskModel("Task 4", "Description 4",true));

        return dummyTasks;
    }


    public void handleDialogClose(DialogInterface dialog) {
        // Responsible for updating the task list after the dialog is closed
        if (tasksAdapter != null) {
            List<TaskModel> updatedTaskList = getUpdatedTaskList();
            tasksAdapter.setTasks(updatedTaskList);
            tasksAdapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
        }
    }



    private List<TaskModel> getUpdatedTaskList() {
        if (tasksAdapter != null) {
            List<TaskModel> updatedList = tasksAdapter.getTasks();
            Log.d("MainActivity", "Updated task count: " + updatedList.size());
            return updatedList;
        } else {
            return Collections.emptyList();
        }
    }

}
