package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
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

    // 개인 정보 테이블
    public static final String TABLE_PERSONAL_INFO = "personal_info";
    public static final String COL_UNIVERSITY = "UNIVERSITY";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_REGION = "REGION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 설문 데이터 테이블 생성
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_SECTION + " TEXT, "
                + COL_QUESTION1 + " TEXT, "
                + COL_QUESTION2 + " TEXT, "
                + COL_QUESTION3 + " TEXT)");

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
    public boolean insertData(String surveySection2, String responseQ6, String section, String responseQ1, String responseQ2, String responseQ3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_SECTION, section);
        contentValues.put(COL_QUESTION1, responseQ1);
        contentValues.put(COL_QUESTION2, responseQ2);
        contentValues.put(COL_QUESTION3, responseQ3);
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

