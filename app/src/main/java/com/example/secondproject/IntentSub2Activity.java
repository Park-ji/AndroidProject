package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentSub2Activity extends AppCompatActivity {

    TextView result;
    Button returnbtn;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub2);
        result = findViewById(R.id.result);
        returnbtn = findViewById(R.id.returnbtn);
        //IntentMainActivity가 IntentSub2Activity 호출하면
        i = getIntent(); //나에게 전달한 인텐트 받음
        String name = i.getStringExtra("name"); //이자바
        result.setText(result.getText() + "\n" + name + "전달받았습니다.");

        //나이를 되돌려드려요 버튼 클릭하면 이자바 나이 '30'이라는 값을 되돌려줌
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("age", 30);
                setResult(2000,i);//매개변수(결과코드, 인텐트: 해당 인텐트는 IntentMainActivity에서 온 인텐트)
                //setResult 메소드 : 매개변수 안의 인텐트에 리턴값을 줌 -> 해당 메소드 안쓰면 리턴값 X
                //해당 결과물을 받으려면? IntentMainActivity에서 onActivityResult 오버라딩
                finish();//현재 액티비티 종료
            }
        });

    }
}

