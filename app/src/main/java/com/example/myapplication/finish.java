package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class finish extends AppCompatActivity {

    DatabaseHelper dbHelper;
    EditText edtUniversity, edtPhone, edtRegion;
    Button btn_1,btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        // DatabaseHelper 초기화
        dbHelper = new DatabaseHelper(this);

        // EditText와 Button 참조
        edtUniversity = findViewById(R.id.edt1);
        edtPhone = findViewById(R.id.edt2);
        edtRegion = findViewById(R.id.edt3);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        // 저장 버튼 클릭 시 데이터 저장
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 각 EditText에서 입력받은 텍스트 가져오기
                String university = edtUniversity.getText().toString();
                String phone = edtPhone.getText().toString();
                String region = edtRegion.getText().toString();

                // 입력값 로그 출력
                Log.d("InputData", "University: " + university + ", Phone: " + phone + ", Region: " + region);

                // 빈 칸이 없는지 확인
                if (university.isEmpty() || phone.isEmpty() || region.isEmpty()) {
                    Toast.makeText(finish.this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 데이터베이스에 삽입
                    boolean isInserted = dbHelper.insertPersonalInfo(university, phone, region);
                    if (isInserted) {
                        Toast.makeText(finish.this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();

                        // 첫 페이지로 이동
                        Intent intent = new Intent(finish.this, MainActivity.class); // 첫 페이지 Activity의 클래스 이름을 넣으세요
                        startActivity(intent);
                        finish(); // 현재 페이지 종료
                    } else {
                        Toast.makeText(finish.this, "저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finish.this, cursordb.class);
                startActivity(intent);
            }
        });
    }
}