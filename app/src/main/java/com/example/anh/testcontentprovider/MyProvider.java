package com.example.anh.testcontentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by anh on 04/09/2017.
 */

public class MyProvider extends ContentProvider {
    private DatabaseHandler databaseHandler;
    private SQLiteDatabase database;
    private static final String AUTHORITY = "com.anh.testcontentprovider";
    private static final String BASE_PATH = "student";
    public static final int STUDENTS = 1;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY +"/"+BASE_PATH);
    private static final UriMatcher sURIMatcher;
    static {
        sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, STUDENTS);
    }
    @Override
    public boolean onCreate() {
        databaseHandler = new DatabaseHandler(getContext());
        database = databaseHandler.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        switch (sURIMatcher.match(uri)){
            case STUDENTS:
                return database.query(StudentStudent.StudentEntry.TABLE_STUDENTS,strings,s,strings1,s1,null,null);
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id;
        switch (sURIMatcher.match(uri)){
            case STUDENTS:
                id = database.insert(StudentStudent.StudentEntry.TABLE_STUDENTS,null,contentValues);
                Uri uri1 = null;
                if(id!=-1){
                    uri1 = ContentUris.withAppendedId(CONTENT_URI,id);
                }
                return uri;
            default:
                throw new IllegalArgumentException("Unknown URI"+ uri);
        }
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        switch (sURIMatcher.match(uri)){
            case STUDENTS:
                return database.delete(StudentStudent.StudentEntry.TABLE_STUDENTS,s,strings);
            default:
                throw new IllegalArgumentException("Unknown URI"+ uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        switch (sURIMatcher.match(uri)){
            case STUDENTS:
                return database.update(StudentStudent.StudentEntry.TABLE_STUDENTS,contentValues,s,strings);
            default:
                throw new IllegalArgumentException("Unknown URI"+ uri);
        }
    }
}
