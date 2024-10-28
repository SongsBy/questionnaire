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
    RadioGroup radioGroupQ1, radioGroupQ2, radioGroupQ3, radioGroupQ4, radioGroupQ5; // 각 질문의 RadioGroup
    Button button1, button2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active4);

        dbHelper = new DatabaseHelper(this);


        radioGroupQ1 = findViewById(R.id.radioGroupQ1); // 질문 6에 대한 RadioGroup
        radioGroupQ2 = findViewById(R.id.radioGroupQ2); // 질문 7에 대한 RadioGroup
        radioGroupQ3 = findViewById(R.id.radioGroupQ3); // 질문 8에 대한 RadioGroup
        radioGroupQ4 = findViewById(R.id.radioGroupQ4); // 질문 9에 대한 RadioGroup
        radioGroupQ5 = findViewById(R.id.radioGroupQ5); // 질문 10에 대한 RadioGroup

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String responseQ6 = ((RadioButton) findViewById(radioGroupQ1.getCheckedRadioButtonId())).getText().toString();
                    String responseQ7 = ((RadioButton) findViewById(radioGroupQ2.getCheckedRadioButtonId())).getText().toString();
                    String responseQ8 = ((RadioButton) findViewById(radioGroupQ3.getCheckedRadioButtonId())).getText().toString();
                    String responseQ9 = ((RadioButton) findViewById(radioGroupQ4.getCheckedRadioButtonId())).getText().toString();
                    String responseQ10 = ((RadioButton) findViewById(radioGroupQ5.getCheckedRadioButtonId())).getText().toString();


                    boolean isInserted = dbHelper.insertData("SurveySection2", responseQ6, responseQ7, responseQ8, responseQ9, responseQ10);
                    if (isInserted) {
                        Toast.makeText(active4.this, "Data Saved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(active4.this, "Data Not Saved", Toast.LENGTH_LONG).show();
                    }


                    Intent intent = new Intent(active4.this, finish.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(active4.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
