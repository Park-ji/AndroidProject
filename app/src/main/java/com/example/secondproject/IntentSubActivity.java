package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentSubActivity extends AppCompatActivity {

    TextView result;
    Button returnbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);
        result = findViewById(R.id.result);
        returnbtn = findViewById(R.id.returnbtn);

        //해당 액티비티에 인텐트 보낸 액티비티가 보낸 데이터 값 확인(ex, name="홍길동")
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        result.setText(result.getText() + "\n" + name + "을(를) 전달받았습니다.");

        //returnbtn클릭시 되돌아가기
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 액티비티 종료, 전의 액티비티로 돌아감
            }
        });
    }
}
