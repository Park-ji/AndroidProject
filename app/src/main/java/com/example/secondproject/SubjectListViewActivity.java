package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SubjectListViewActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list_view);
        lv = findViewById(R.id.lv); //cf) res폴더에서 가져다 쓰는 resource는 안드로이드 시스템에 의해서 정수로 관리

        //1. 배열생성 : SubjectVO 5개 생성 - ArrayList 생성
        ArrayList<SubjectVO> list = new ArrayList<>();
        list.add(new SubjectVO(R.drawable.android, "안드로이드 과정", "안드로이드 기본 원리 학습 중입니다."));
        list.add(new SubjectVO(R.drawable.google, "구글 과정", "구글 맵 이용 방법 배웁니다."));
        list.add(new SubjectVO(R.drawable.iphone, "아이폰 과정", "아이폰 과정은 개설 예정입니다."));
        list.add(new SubjectVO(R.drawable.html5, "HTML5 과정", "웹개발에 필요한 기술입니다."));
        list.add(new SubjectVO(R.drawable.jquery, "JQuery 과정", "동적인 웹화면을 만드는 기술입니다."));

        //2. Adatper 생성 : xxxxAdapter 생성
        //매개변수
        //사용 컨텍스트 : this(SubjectListViewActivity)
        //사용 레이아웃 :
        SubjectAdapter adapter = new SubjectAdapter(this, list);

        //3. 연결 : lv.setAdapter();
        lv.setAdapter(adapter);
    }
}
