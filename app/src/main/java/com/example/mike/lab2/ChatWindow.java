package com.example.mike.lab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ChatWindow";
    private ListView listView;
    private EditText editText;
    private Button sendButton;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ChatAdapter messageAdapter;
    private ChatDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(ACTIVITY_NAME, "is onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.EditTest3);
        sendButton = (Button) findViewById(R.id.sendButton);

        helper = new ChatDatabaseHelper(this);
        Cursor cursor = helper.getData();

        while(cursor.moveToNext()){
            arrayList.add( cursor.getString( cursor.getColumnIndex(helper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + cursor.getString( cursor.getColumnIndex(helper.KEY_MESSAGE)));
        }

        messageAdapter = new ChatAdapter( this );
        listView.setAdapter (messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String newMsg = editText.getText().toString();
                arrayList.add( newMsg );
                helper.insertData( newMsg );
                messageAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }
    @Override
    protected void onStart() {
        Log.i(ACTIVITY_NAME, "is onStart");
        super.onStart();
    }

    @Override
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "is onDestroy");
        super.onDestroy();
        helper.close();
    }


    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount(){
            return arrayList.size();
        }

        public String getItem(int position){
            return arrayList.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2 == 0){
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else{
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText( getItem(position) );
            return result;
        }
    }
}