package com.example.resumebuilderapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
Context context;
    public DBHelper(Context context) {
        super(context,"Users.db",null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (Id INTEGER Primary key  Autoincrement,firstName TEXT NOT NULL,lastName TEXT NOT NULL,userName TEXT not null,password TEXT not null," +
                "email TEXT NOT null,gender TEXT NOT NULL,phoneNumber TEXT NOT NULL,city TEXT NOT NULL )");
        db.execSQL("create table resumes (college TEXT NOT NULL,degreeName TEXT NOT NULL,cgpa INTEGER NOT NULL,objective TEXT NOT NULL,experience TEXT NOT NULL,skills TEXT NOT NULL,"+
                "website TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
