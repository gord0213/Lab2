package com.example.mike.lab2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mike on 2016-10-17.
 */
public class ChatDatabaseHelper extends SQLiteOpenHelper {
    protected static final String ACTIVITY_NAME = "ChatDatabaseHelper";
    protected static final String DATABASE_NAME = "chtDatabase";
    protected static int VERSION_NUMBER = 4;
    protected static final String KEY_ID = "Chat_ID";
    protected static final String KEY_MESSAGE = "Text_Messages";
    protected static final String TABLE_NAME = "Chat_Messages";


    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUMBER);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "onCreate" );
        String CREATE_CHAT_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MESSAGE + " TEXT)";
        Log.i(ACTIVITY_NAME,  "Testing: " + CREATE_CHAT_TABLE);
        db.execSQL(CREATE_CHAT_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i(ACTIVITY_NAME, "onUpdate version " + oldVersion +" to new database version: " +  newVersion );
    }

    public void insertData(String msg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MESSAGE, msg);
        long insertResult = db.insert(TABLE_NAME, null, contentValues);
        Log.i(ACTIVITY_NAME, "insert data resul+: " + insertResult + ", Data inserted: " + msg);
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}