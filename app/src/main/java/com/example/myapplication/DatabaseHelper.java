package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Survey.db";

    // 설문 데이터 테이블
    public static final String TABLE_NAME = "survey_table";
    public static final String COL_ID = "ID";
    public static final String COL_SECTION = "SECTION";
    public static final String COL_QUESTION1 = "QUESTION1";
    public static final String COL_QUESTION2 = "QUESTION2";
    public static final String COL_QUESTION3 = "QUESTION3";
    public static final String COL_QUESTION4 = "QUESTION4";
    public static final String COL_QUESTION5 = "QUESTION5";
    public static final String COL_QUESTION6 = "QUESTION6";
    public static final String COL_QUESTION7 = "QUESTION7";
    public static final String COL_QUESTION8 = "QUESTION8";
    public static final String COL_QUESTION9 = "QUESTION9";
    public static final String COL_QUESTION10 = "QUESTION10";

    // 개인 정보 테이블
    public static final String TABLE_PERSONAL_INFO = "personal_info";
    public static final String COL_UNIVERSITY = "UNIVERSITY";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_REGION = "REGION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    public Cursor getSurveyData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // 개인 정보 데이터 가져오는 메서드 추가
    public Cursor getPersonalInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PERSONAL_INFO, null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 설문 데이터 테이블 생성
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SECTION + " TEXT, "
                + COL_QUESTION1 + " TEXT, "
                + COL_QUESTION2 + " TEXT, "
                + COL_QUESTION3 + " TEXT,"
                + COL_QUESTION4 + " TEXT,"
                + COL_QUESTION5 + " TEXT,"
                + COL_QUESTION6 + " TEXT,"
                + COL_QUESTION7 + " TEXT,"
                + COL_QUESTION8 + " TEXT,"
                + COL_QUESTION9 + " TEXT,"
                + COL_QUESTION10 + " TEXT)");


        // 개인 정보 테이블 생성
        db.execSQL("CREATE TABLE " + TABLE_PERSONAL_INFO +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_UNIVERSITY + " TEXT, "
                + COL_PHONE + " TEXT, "
                + COL_REGION + " TEXT)");
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAL_INFO);
        onCreate(db);
    }

    // 설문 응답 데이터를 저장하는 메서드
    public boolean insertData(String surveySection,String responseQ1,String responseQ2, String responseQ3, String responseQ4, String responseQ5, String responseQ6, String responseQ7, String responseQ8, String responseQ9, String responseQ10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SECTION, surveySection);
        contentValues.put(COL_QUESTION1, responseQ1);
        contentValues.put(COL_QUESTION2, responseQ2);
        contentValues.put(COL_QUESTION3, responseQ3);
        contentValues.put(COL_QUESTION4, responseQ4);
        contentValues.put(COL_QUESTION5, responseQ5);
        contentValues.put(COL_QUESTION6, responseQ6);
        contentValues.put(COL_QUESTION7, responseQ7);
        contentValues.put(COL_QUESTION8, responseQ8);
        contentValues.put(COL_QUESTION9, responseQ9);
        contentValues.put(COL_QUESTION10, responseQ10);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            Log.e("DatabaseHelper", "Error inserting data into survey_table");
        } else {
            Log.d("DatabaseHelper", "Data inserted successfully into survey_table");
        }
        return result != -1;
    }

    // 개인 정보를 저장하는 메서드
    public boolean insertPersonalInfo(String university, String phone, String region) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_UNIVERSITY, university);
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_REGION, region);
        long result = -1;
        try {
            result = db.insert(TABLE_PERSONAL_INFO, null, contentValues);
            Log.d("DatabaseHelper", "Data inserted successfully into personal_info table");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting data: " + e.getMessage());
        }
        return result != -1;
    }

}

