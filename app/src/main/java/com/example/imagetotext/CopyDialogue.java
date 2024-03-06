package com.example.imagetotext;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class CopyDialogue {

    private AlertDialog dialogue;
    private final Activity activity;

    CopyDialogue(Activity myActivity){
        activity = myActivity;
    }

    void startCopyDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.copy_dialogue, null));
        builder.setCancelable(true);


        dialogue = builder.create();
        dialogue.show();
    }


    void dismissDialogue(){
        dialogue.dismiss();
    }
}
