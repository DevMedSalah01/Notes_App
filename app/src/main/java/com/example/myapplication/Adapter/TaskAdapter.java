package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddNewTask;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Model.TaskModel;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Context context;
    private List<TaskModel> taskList;
    private MainActivity activity;

    public TaskAdapter(Context context, MainActivity activity) {
        this.context=context;
        this.activity = activity;
        this.taskList = new ArrayList<>();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskModel item = taskList.get(position);
        holder.task.setText(item.getTaskName());
        holder.task.setChecked(item.isCompleted());
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle task status change if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    public List<TaskModel> getTasks() {
        return taskList != null ? taskList : new ArrayList<>();
    }


    public Context getContext() {
        return context;
    }

    public void setTasks(List<TaskModel> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged(); //to notify the viewmodel(adapter)
        Log.d("TaskAdapter", "Data updated. New task count: " + getItemCount());
    }
    public void addItem(TaskModel newItem) {
        taskList.add(newItem);
        Log.d("TaskAdapter", "Updated tasks " + getTasks());
        notifyItemInserted(taskList.size() - 1); // Notify adapter about the new item
    }

    public void deleteItem(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        TaskModel item = taskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("task", item.getTaskName());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
        notifyItemChanged(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}
