package com.example.anh.testcontentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button add;
    private ListView listView;
    private List<Student> students;
    private ArrayAdapter<Student> studentArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        students = getData();
        listView = (ListView) findViewById(R.id.listview);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
        studentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listView.setAdapter(studentArrayAdapter);
    }

    private List<Student>  getData(){
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MyProvider.CONTENT_URI,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                studentList.add(new Student(id,name,age));
                cursor.moveToNext();
            }
        }
        return  studentList;
    }

    @Override
    public void onClick(View view) {
        long id = new Random().nextLong();
        int age = new Random().nextInt();
        ContentValues values = new ContentValues();
        values.put(StudentStudent.StudentEntry.ID,id);
        values.put(StudentStudent.StudentEntry.NAME,"name");
        values.put(StudentStudent.StudentEntry.AGE,age);
        if (getContentResolver().insert(MyProvider.CONTENT_URI, values)!=null) {
            Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG)
                    .show();
            students.clear();
            students.addAll(getData());
            studentArrayAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(this, "Insert failed", Toast.LENGTH_SHORT).show();
        }
    }
}
