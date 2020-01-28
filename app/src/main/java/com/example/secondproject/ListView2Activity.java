package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListView2Activity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        lv = findViewById(R.id.lv);
        HashMap<String, String> phones = new HashMap( );
        phones.put("name", "이자바");
        phones.put("tel","010-2222-3333");

        HashMap<String, String> phones2 = new HashMap( );
        phones2.put("name", "홍길동");
        phones2.put("tel","010-1234-5678");

        HashMap<String, String> phones3 = new HashMap( );
        phones3.put("name", "성윤정");
        phones3.put("tel","010-5678-1234");

        ArrayList<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
        datalist.add(phones);
        datalist.add(phones2);
        datalist.add(phones3);

        //ArrayList 내부 데이터 형태가 HashMap인 경우 사용하는 Adapter? SimpleAdatper;
        //simple_list_item_2 : 2개의 item을 같이 보여줘야함(name, tel)


        SimpleAdapter adapter = new SimpleAdapter
                ( this,
                        datalist,
                        android.R.layout.simple_list_item_2, //한 아이템에 두개를 보여줌
                        new String[]{"name","tel"}, // 키 name, 키 tel
                        new int[]{android.R.id.text1, android.R.id.text2}); // 안드로이드 내장 text1 : 글씨크기

        lv.setAdapter(adapter);

    }
}
