package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class active4 extends AppCompatActivity {

    TextView textView7, textView8,textView9, textView10 , textView11 , textView12, textView13;
    RadioButton radioButton1 , radioButton2, radioButton5, radioButton6, radioButton9 ,radioButton10, radioButton11, radioButton12,radioButton13, radioButton14,radioButton15,radioButton16,radioButton17, radioButton18,radioButton19,radioButton20;
    Button button1,button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.active4);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);



            button2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                finish();
                }
            });

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(active4.this,finish.class);;
                    startActivity(intent);
                }
            });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}