package com.example.android.viewfirebase;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DbHelper(this);

        final Button b2 = (Button) findViewById(R.id.login);
        insert_item();

    }

    public boolean search(String username, String spassword) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {mDbHelper.COLUMN_USERNAME, mDbHelper.COLUMN_PASSWORD};
        Cursor cursor = db.query(mDbHelper.TABLE_NAME, projection, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int nameColumnIndex = cursor.getColumnIndex(mDbHelper.COLUMN_USERNAME);
            int passwordColumnIndex = cursor.getColumnIndex(mDbHelper.COLUMN_PASSWORD);
            String currentName = cursor.getString(nameColumnIndex);
            String currentPass = cursor.getString(passwordColumnIndex);
            if (currentName.equals(username) && currentPass.equals(spassword))
                return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public void insert_item() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mDbHelper.COLUMN_USERNAME, "admin");
        values.put(mDbHelper.COLUMN_PASSWORD, "root");
        db.insert(mDbHelper.TABLE_NAME, null, values);
        db.close();
    }

    public void onSearch(View view) {
        final Button b1 = (Button) findViewById(R.id.DisplayImagesButton);
        final EditText name = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.pass);
        final String s_name = name.getText().toString();
        final String s_pass = pass.getText().toString();
        final Button b2 = (Button) findViewById(R.id.login);

        if (search(s_name, s_pass)) {
            name.setVisibility(View.GONE);
            pass.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            b1.setVisibility(View.VISIBLE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DisplayImagesActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect login details", Toast.LENGTH_SHORT).show();
        }
    }
}