package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class cursordb extends AppCompatActivity {
    DatabaseHelper dbHelper;
    TextView tvDataDispey;
    Button btnShowSurvey,btnShowPersonalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cursordb);

        dbHelper = new DatabaseHelper(this);
        btnShowSurvey = findViewById(R.id.btnShowSurvey);
        btnShowPersonalInfo = findViewById(R.id.btnShowPersonalInfo);
        tvDataDispey = findViewById(R.id.tvDataDisplay);

        btnShowSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getSurveyData();
                if (cursor.getCount()==0){
                    tvDataDispey.setText("데이터가 없습니다.");
                    return;
                }
                StringBuilder data = new StringBuilder();

                while (cursor.moveToNext()){
                    data.append("ID:").append(cursor.getInt(0)).append("\n");
                    data.append("Section:").append(cursor.getInt(1)).append("\n");

                    for (int i = 2; i <=11;i++){
                        data.append("Question").append(i-1).append(":").append(cursor.getString(i)).append("\n");
                    }
                    data.append("\n");
                }
                    tvDataDispey.setText(data.toString());
            }
        });
        btnShowPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.getPersonalInfo();
                if (cursor.getCount() == 0) {
                    tvDataDispey.setText("데이터가 없습니다.");
                    return;
                }

                StringBuilder data = new StringBuilder();

                while (cursor.moveToNext()) {
                    data.append("ID:").append(cursor.getInt(0)).append("\n");
                    data.append("University:").append(cursor.getString(1)).append("\n");
                    data.append("Phone:").append(cursor.getString(2)).append("\n");
                    data.append("Region:").append(cursor.getString(3)).append("\n\n");
                }
                tvDataDispey.setText(data.toString());
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}