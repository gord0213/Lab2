package com.example.mike.lab2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {
    private String item1Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Something Michael Gordanier has written", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        item1Message = "HOME menu is selected (default)";
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {
            case R.id.actionHome:
                Log.d("Toolbar", "Option 1 selected");
                Snackbar.make(findViewById(android.R.id.content), item1Message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.actionExit:
                Log.d("Toolbar", "Option 2 selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Do you want to exit");
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.actionEdit:
                Log.d("Toolbar", "Option 3 selected");
                showChangeMessageDialog();
                break;
            case R.id.actionAbout:
                Toast.makeText(TestToolbar.this, "Version 1.0, Michael Gordanier", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private void showChangeMessageDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_custom, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.new_message);

        dialogBuilder.setPositiveButton(R.string.change_message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item1Message = edt.getText().toString();
                Snackbar.make(findViewById(android.R.id.content), "Message changed", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        dialogBuilder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
}
