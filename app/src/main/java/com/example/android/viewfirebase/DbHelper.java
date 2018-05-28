package com.example.android.viewfirebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "details";
    //    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "pwd";
    private static final String DB_NAME = "user";
    private static final int DB_VER = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(CREATE_DB_TABLE);
//        insert_item();

    }

    /*public void insert_item() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "admin");
        values.put(COLUMN_PASSWORD, "root");
        db.insert(TABLE_NAME, null, values);
        db.close();
    }*/

   /* public static boolean search(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_USERNAME,COLUMN_PASSWORD};
        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,null);
        int nameColmunIndex = cursor.getColumnIndex(COLUMN_USERNAME);
        int passwordColmunIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
        String currentName=cursor.getString(nameColmunIndex);
        String currentPass=cursor.getString(passwordColmunIndex);
        if(currentName.equals(username)&&currentPass.equals(password))
            return true;
        return false;
    }*/
}
