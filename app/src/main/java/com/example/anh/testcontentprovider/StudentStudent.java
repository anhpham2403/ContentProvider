package com.example.anh.testcontentprovider;

import android.provider.BaseColumns;

/**
 * Created by anh on 05/09/2017.
 */

public class StudentStudent {
    public StudentStudent() {
    }
    public static class StudentEntry implements BaseColumns{
        public static final String TABLE_STUDENTS = "student";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }
}
