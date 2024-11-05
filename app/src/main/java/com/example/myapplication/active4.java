package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class active4 extends AppCompatActivity {

    DatabaseHelper dbHelper;
    RadioGroup radioGroupQ6, radioGroupQ7, radioGroupQ8, radioGroupQ9, radioGroupQ10; // active4의 각 질문에 대한 RadioGroup
    Button button1, button2;
    String responseQ1, responseQ2, responseQ3, responseQ4, responseQ5; // 이전 액티비티에서 전달받은 응답

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active4);

        dbHelper = new DatabaseHelper(this);

        // Intent를 통해 third3에서 전달된 데이터를 받음
        Intent intent = getIntent();
        responseQ1 = intent.getStringExtra("responseQ1");
        responseQ2 = intent.getStringExtra("responseQ2");
        responseQ3 = intent.getStringExtra("responseQ3");
        responseQ4 = intent.getStringExtra("responseQ4");
        responseQ5 = intent.getStringExtra("responseQ5");

        // 레이아웃의 RadioGroup과 버튼을 연결
        radioGroupQ6 = findViewById(R.id.radioGroupQ6); // 질문 6에 대한 RadioGroup
        radioGroupQ7 = findViewById(R.id.radioGroupQ7); // 질문 7에 대한 RadioGroup
        radioGroupQ8 = findViewById(R.id.radioGroupQ8); // 질문 8에 대한 RadioGroup
        radioGroupQ9 = findViewById(R.id.radioGroupQ9); // 질문 9에 대한 RadioGroup
        radioGroupQ10 = findViewById(R.id.radioGroupQ10); // 질문 10에 대한 RadioGroup
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        // 저장 버튼 클릭 이벤트 처리
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // 각 질문에 대한 선택된 응답을 가져옴
                    String responseQ6 = ((RadioButton) findViewById(radioGroupQ6.getCheckedRadioButtonId())).getText().toString();
                    String responseQ7 = ((RadioButton) findViewById(radioGroupQ7.getCheckedRadioButtonId())).getText().toString();
                    String responseQ8 = ((RadioButton) findViewById(radioGroupQ8.getCheckedRadioButtonId())).getText().toString();
                    String responseQ9 = ((RadioButton) findViewById(radioGroupQ9.getCheckedRadioButtonId())).getText().toString();
                    String responseQ10 = ((RadioButton) findViewById(radioGroupQ10.getCheckedRadioButtonId())).getText().toString();

                    // 데이터베이스에 모든 응답 저장
                    boolean isInserted = dbHelper.insertData("SurveySection", responseQ1, responseQ2, responseQ3, responseQ4, responseQ5, responseQ6, responseQ7, responseQ8, responseQ9, responseQ10);
                    if (isInserted) {
                        Toast.makeText(active4.this, "Data Saved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(active4.this, "Data Not Saved", Toast.LENGTH_LONG).show();
                    }

                    // finish 액티비티로 이동
                    Intent intent = new Intent(active4.this, finish.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(active4.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 취소 버튼 클릭 이벤트 처리
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // WindowInsets 설정
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
