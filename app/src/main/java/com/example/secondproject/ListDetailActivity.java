package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListDetailActivity extends AppCompatActivity {

    ListView detail;
    String detailarray[];
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);


        detail = findViewById(R.id.detail);
        detailarray = new String[3];

        //1. 데이터 배열 만들기
        Intent i = getIntent();
        String subject = i.getStringExtra("subject");
        detailarray[0] = "과정명 : " + subject;
        if(subject.equals("java") || subject.equals(("자바"))){
            detailarray[1] = "설명 : 자바를 기본 문법을 배웁니다.";
            detailarray[2] = "점수 : 90";
        }
        else{
            detailarray[1] = "설명 : 정보 없음";
            detailarray[2] = "점수 : 0";
        }

        //2. Adapter 만들기
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, detailarray);
        //cf) simple_list_item_2 : 2개씩 보여줌 -> 현재 데이터 개수 3개이므로 에러발생

        //3. ListView와 연결
        detail.setAdapter(adapter);

        //Cf) 다양한 자료구조
        // 배열 + ArrayList : 인덱스
        // HashMap : (key, value)


    }
}
















