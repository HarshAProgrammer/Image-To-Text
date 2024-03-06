package com.example.imagetotext;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class CameraDialogue {
    private AlertDialog dialogue;
    private final Activity activity;

    CameraDialogue(Activity myActivity){
        activity = myActivity;
    }

    void startExpensiveDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.camera_dialogue, null));
        builder.setCancelable(true);


        dialogue = builder.create();
        dialogue.show();
    }


    void dismissDialogue(){
        dialogue.dismiss();
    }
}
