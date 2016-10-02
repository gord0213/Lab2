package com.example.mike.lab2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);//GridLayout);//
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        Switch switchButton = (Switch) findViewById(R.id.SwitchButton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text;
                int duration;
                Toast toast;
                try {
                    if (isChecked) {
                        text = "Switch is On"; //=Toast.LENGTH_LONG is ODD
                        duration = Toast.LENGTH_SHORT;
                    } else {
                        text = "Switch is OFF"; //
                        duration = Toast.LENGTH_LONG;
                    }
                    toast = Toast.makeText(ListItemsActivity.this, text, duration); //Toast.makeText(this, text, duration);
                    toast.show();
                } catch (Exception e) {
                    Log.d("Crash!!!", e.getMessage());
                }
            }
        });


        }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
            Bundle bundle = data.getExtras();
            Bitmap imageBitMap = (Bitmap) bundle.get("data");
            imageButton.setImageBitmap(imageBitMap);
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "is onResume");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "is onStart");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "is onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "is onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "is onDestroy");
    }
    protected void onCheckboxClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        }).show();

    }
}
