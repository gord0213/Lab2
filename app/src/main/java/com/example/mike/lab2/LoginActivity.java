package com.example.mike.lab2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(ACTIVITY_NAME, "is onCreate");
        System.out.print("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        EditText email = (EditText) findViewById(R.id.EditText);
        SharedPreferences  preferences = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        email.setText(preferences.getString("DefaultEmail", "email@domain.com"));
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
    protected void onDestroy() {
        Log.i(ACTIVITY_NAME, "is onDestroy");
        System.out.print("onDestroy");
        super.onDestroy();
    }
    public void onClickLogin(View view){

        EditText email = (EditText) findViewById(R.id.EditText);
        SharedPreferences sharedPreferences = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        SharedPreferences.Editor writer = sharedPreferences.edit();
        writer.putString("DefaultEmail", email.getText().toString());
        writer.commit();
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);
    }
}
