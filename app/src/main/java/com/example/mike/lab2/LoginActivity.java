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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        Button loginButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
                EditText email = (EditText) findViewById(R.id.EditText);

                System.out.println(email);
                Context context = LoginActivity.this;
                SharedPreferences sharedPreferences = context.getSharedPreferences(getString(R.string.LoginButton), Context.MODE_PRIVATE);

                SharedPreferences.Editor writer = sharedPreferences.edit();
                writer.putString("email", email.getText().toString());
                writer.apply();
            }
        });
    }




    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "is onResume");
    }

    @Override
    protected void onStart(){
        super.onStart();
        EditText email = (EditText) findViewById(R.id.EditText);
        Log.i(ACTIVITY_NAME, "is onStart");
        SharedPreferences  preferences = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);
        email.append(preferences.getString("DefaultEmail", "email@domain.com"));
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "is onDestroy");
    }
}
