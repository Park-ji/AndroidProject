package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button; //Button객체 import
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //안드로이드 스튜디오에 서 만들어지는 첫번째 실행 클래스? MainActivity
    //특성 1. AppCompatActivity를 상속받을 것(Activity로서 작동할 수 있도록)
    //특성 2. onCreate함수를 오버라이딩



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //특성 3. 자바파일?액티비티 = 시작화면을 만들어줌
        //액티비티 내부에서 사용하는 다른 자원들(xml, 이밎...) ? resource
        //리소스는 이름을 붙여서 사용하게 되어있음
        //모든 리소스는? res폴더에 넣음
        //res폴더의 내부 폴더들은 건들지 말것
        setContentView(R.layout.activity_main);
        //R? 리소스가 있는 곳으로 가라 = res폴더
        //layout? layout폴더로 가라
        //activity_main? activity_main.xml파일을 참고함(확장자는 뺌 - 모든 리소스 파일(ex, 이미지도))
        //안드로이드에서는 파일들을 객체취급해서 res폴더안의 layout폴더를 나타내느 표현은 R.layout이라고 표현한다.
        //즉, 화면을 만드는데 activity_main.xml에 있는 것을 이용하겠다라는 의미


        //액션바 영역 편집 가능
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //아이콘(=이미지)와 제목 수정
        getSupportActionBar().setIcon(R.drawable.api30);//java파일에서 다른 파일을 의미하는 것은 '리소스'(R)
        getSupportActionBar().setTitle("세컨드액티비티");//초기 title은 프로젝트 이름

        //버튼 1 클릭했을 때 이벤트 처리
        //1) 클릭한 버튼이 '버튼1'임을 확인하는 방법? id  --> java파일에서 만들어지지 않은 속성에 접근할때는 무조건 'R'로 접근
        //xml에서는 'Button 태그' --> java에서는 'Button 객체'

        final Button btn1 =  findViewById(R.id.btn1);
        Button btn2 =  findViewById(R.id.btn2);
        Button btn3 =  findViewById(R.id.btn3);
        Button btn4 =  findViewById(R.id.btn4);

        //2) 클릭 이벤트 처리 -> 이클립스의 androidtest파일 참고(a,b1,b2,b3 클래스 참고)
        //b1 : 인터페이스 직접 상속
        //b2 : innerclass 사용
        //b3 : anonymousclass 사용

        //2-1) anonymousclass 방식으로 사용
        //setOnClickListner 함수 사용
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override //Code->Override Methods
            public void onClick(View v) { //클릭 이벤트 처리 메서드 오버라이드
                //alert? 안드로이드에서는 toast
                Toast.makeText(getApplicationContext(), btn1.getText()+"선택하셨습니다.", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(), ((Button)v).getText()+"선택하셨습니다.", Toast.LENGTH_LONG).show();
                //Toast.LENGTH_LONG : 팝업창이 띄워지는 시간지정, 약 5초
            }
        });

        //2-2)

    }
}


























