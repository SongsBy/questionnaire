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

public class third3 extends AppCompatActivity {

    DatabaseHelper dbHelper;
    RadioGroup radioGroupQ1, radioGroupQ2, radioGroupQ3, radioGroupQ4, radioGroupQ5; // 각 질문의 RadioGroup
    Button button1, button2;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third3);

        dbHelper = new DatabaseHelper(this);


        radioGroupQ1 = findViewById(R.id.radioGroupQ1); // 질문 1에 대한 RadioGroup
        radioGroupQ2 = findViewById(R.id.radioGroupQ2); // 질문 2에 대한 RadioGroup
        radioGroupQ3 = findViewById(R.id.radioGroupQ3); // 질문 3에 대한 RadioGroup
        radioGroupQ4 = findViewById(R.id.radioGroupQ4); // 질문 4에 대한 RadioGroup
        radioGroupQ5 = findViewById(R.id.radioGroupQ5); // 질문 5에 대한 RadioGroup

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String responseQ1 = ((RadioButton) findViewById(radioGroupQ1.getCheckedRadioButtonId())).getText().toString();
                    String responseQ2 = ((RadioButton) findViewById(radioGroupQ2.getCheckedRadioButtonId())).getText().toString();
                    String responseQ3 = ((RadioButton) findViewById(radioGroupQ3.getCheckedRadioButtonId())).getText().toString();
                    String responseQ4 = ((RadioButton) findViewById(radioGroupQ4.getCheckedRadioButtonId())).getText().toString();
                    String responseQ5 = ((RadioButton) findViewById(radioGroupQ5.getCheckedRadioButtonId())).getText().toString();


                    boolean isInserted = dbHelper.insertData("SurveySection", responseQ1, responseQ2, responseQ3, responseQ4, responseQ5);
                    if (isInserted) {
                        Toast.makeText(third3.this, "Data Saved", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(third3.this, "Data Not Saved", Toast.LENGTH_LONG).show();
                    }


                    Intent intent = new Intent(third3.this, active4.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(third3.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                }
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
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
