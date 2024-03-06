package com.example.imagetotext;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class DeleteDialogue {

    private AlertDialog dialogue;
    private final Activity activity;

    DeleteDialogue(Activity myActivity){
        activity = myActivity;
    }

    void startDeleteDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.delete_dialogue, null));
        builder.setCancelable(true);


        dialogue = builder.create();
        dialogue.show();
    }


    void dismissDialogue(){
        dialogue.dismiss();
    }
}
