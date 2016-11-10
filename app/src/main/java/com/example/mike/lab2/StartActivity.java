package com.example.mike.lab2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    private Button viewWeatherButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(ACTIVITY_NAME, "is onCreate");
        System.out.print("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        viewWeatherButton = (Button) findViewById(R.id.weatherButton);
        viewWeatherButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent weatherIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                Log.i(ACTIVITY_NAME, "Go to weather forcast");
                startActivity(weatherIntent);
            }
        });

    }

    public void StartChatButtonClicked(View view){
        Log.i(ACTIVITY_NAME, "User clicked Start Chat");
        Intent intent = new Intent(StartActivity.this, MessageListActivity.class);
        startActivity(intent);
    }



    protected void onActivityResult(int requestCode, int reponseCode, Intent data) {
        if ((requestCode == 5)&&(reponseCode == Activity.RESULT_OK)) {
            Log.i(ACTIVITY_NAME, "retuned activity result StartActivity");
            CharSequence text;
            int duration;
            Toast toast;
            try {
                int messagePassed = data.getIntExtra("Response", 0);
                duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(StartActivity.this, messagePassed, duration);
                toast.show();
            } catch (Exception e) {
                Log.d("Crash!!!", e.getMessage());
            }

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
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //LoginActivity.this.startActivity( new Intent(LoginActivity.this, StartActivity.class));
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 5);
            }
        });
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
}
