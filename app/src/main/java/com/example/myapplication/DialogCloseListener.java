package com.example.myapplication;

import android.content.DialogInterface;
//to handle what happens after the dialog is closed
public interface DialogCloseListener {
    public void handleDialogClose(DialogInterface dialog); //method to be implemented
}