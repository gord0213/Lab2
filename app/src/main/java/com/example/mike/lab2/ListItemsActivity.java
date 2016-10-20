package com.example.mike.lab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(ACTIVITY_NAME, "is onCreate");
        System.out.print("onCreate");
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
        Log.i(ACTIVITY_NAME, "is onResume");
        System.out.print("onResume");
        super.onResume();
    }

    @Override
    protected void onStart(){
        Log.i(ACTIVITY_NAME, "is onStart");
        System.out.print("onStart");
        super.onStart();

    }
    @Override
    protected void onPause(){
        Log.i(ACTIVITY_NAME, "is onPause");
        System.out.print("onPause");
        super.onPause();
    }
    @Override
    protected void onStop(){
        Log.i(ACTIVITY_NAME, "is onStop");
        System.out.print("onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "is onDestroy");
        System.out.print("onDestroy");
        super.onDestroy();
    }

    public void onCheckboxClicked(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent resultIntent = new Intent(  );

                        resultIntent.putExtra("Response", R.string.responseToast);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();

                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        }).show();

    }
}
