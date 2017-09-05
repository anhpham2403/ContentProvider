package com.example.anh.testcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anh on 04/09/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + StudentStudent.StudentEntry.TABLE_STUDENTS + " (" + StudentStudent.StudentEntry.ID
                + " INTEGER PRIMARY KEY," + StudentStudent.StudentEntry.NAME + " TEXT," + StudentStudent.StudentEntry.AGE
                + " INTEGER" + " )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + StudentStudent.StudentEntry.TABLE_STUDENTS);
        onCreate(sqLiteDatabase);
    }
}
