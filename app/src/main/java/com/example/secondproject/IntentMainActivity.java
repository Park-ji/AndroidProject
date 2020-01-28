package com.example.secondproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class IntentMainActivity extends AppCompatActivity {

    TextView state;
    Button callbtn, callbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);
        state = findViewById(R.id.state);
        callbtn = findViewById(R.id.callbtn);
        callbtn2 = findViewById(R.id.callbtn2);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//리턴결과 없는 인텐트 호출
                //this? 현재 액티비티를 의미
                //callbtn.setOn..안에 정의된 this? callbtn..인 innerclass를 의미하므로 오류!
                Intent i = new Intent(getApplicationContext(), IntentSubActivity.class); //매개변수(누가, 누구를)
                //액티비티 전환시 전달할 데이터가 필요하면?
                i.putExtra("name", "홍길동"); //전달 가능한 데이터? 자바의 모든 객체
                //리턴값이 필요없는 호출 방법 : startActivity()
                startActivity(i);//i에 들어있는 액티비티 실행
            }
        });//callbtn

        callbtn2.setOnClickListener(new View.OnClickListener() {//리턴결과 있는 인텐트 호출
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), IntentSub2Activity.class);
                i.putExtra("name","이자바 ");
                //Intent호출한다음에 리턴값을 받을때 사용하는 메서드 = 리턴값이 필요한 호출 방법 : startActivityForResult()
                //전달시, 인텐트 뿐만 아니라 요청코드를 같이 보냄
                startActivityForResult(i, 1000); //requestCode는 정수값
                //1000번? 현재 액티비티(IntentMainActivity)을 나타내는 코드가 1000번이라고 보냄(코드 식별을 위한것)
                //여러개의 인텐트 호출시 각기 다른 번호로 보낼수도 있음
            }
        });//callbnt2
    }//oncreate end

    //onActivityResult 오버라이딩
    //요청코드? 1000 -> 응답코드? 2000 으로 지정함
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); //지우면 안됨 -> super클래스에 뭐 있으면 그것먼저 실행하라는 의미
        if(requestCode==1000&&resultCode==2000){//나한테 정상적인 응답으로 준건지 체크하는 조건문
            int age = data.getIntExtra("age",0); //잘못읽을 수 도 있으므로 잘못읽으면(ex, age2라고 하거나) 0으로 반환
            state.setText(state.getText() + "\n" + age + "값을 돌려받았습니다.");
        }
    }
}//activity end


