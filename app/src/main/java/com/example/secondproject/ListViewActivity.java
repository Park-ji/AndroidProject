package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ListView lv;
    //이벤트 처리 함수 안에서도 사용하기 위한 변수 설정 방법
    //1. 멤버 변수 : String data[] ;
    //단, 자바에서 배열의 선언을 따로 줄때는 구현 할때? data = new String[]{..}; 라고 해줘야함
    //2. 지역 변수일 경우? final 예약어 사용
    String data[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        lv = findViewById(R.id.lv);

        //1. ListView 내부에 포함할 텍스트 문자열 배열로 준비 (1.1 또는 1.2 사용)
        //1-1.
        // data[] = new String[] {"자바","SQL","ANDROID","SPRING","HTML5","MYBAITS","SERVLET"};
        //final String data[] = {"자바","SQL","ANDROID","SPRING","HTML5","MYBAITS","SERVLET"};

        //1-2. res/values/arryas.xml(AutoActivity 에서 사용) : 여러 액티비티에서 공동의 배열 사용시 용이
        //중요한점? 파일명 보단 태그가 <string-array> 로 시작하면 됨
        Resources res = getResources();
       data = res.getStringArray(R.array.autolist); //배열 입력

        //ArrayList로 변환
        //필수적인 작업은 아니지만 하려는 작업이 추가의 작업도 필요하기 때문에
        //동적인 arraylist로 바꿈
        final ArrayList datalist = new ArrayList();
        for(int i=0;i<data.length;i++){
            datalist.add(data[i]);
        }

        //2. Adapter 구현
        //기능 : 어떤 액티비티에 어떤 데이터들을 어떤 레이아웃으로 구현할지 설정
        //종류 : 다양한 종류 중 ArrayAdapter사용
        //정의 매개변수 : 현재 액티비티, 사용할 레이아웃 종류(각 데이터를 어떻게 보여줄지 결정), ListView를 구성할 데이터
        //android.R.layout : 안드로이드 내장 레이아웃 정의
        //ex) 리스트뷰 내부에 하나의 아이템씩 보여주는 내장 레이아웃 : android.R.layout.simple_list_item_1
        //R.layout : layout폴더에 사용자가 만든 레이아웃 정의
        //1) 세로 나열
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //2) 체크 형태
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,data);
        //3) 라디오 버튼 형태
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,data);
        //4) 체크 박스 형태 : 다중선택 가능
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,data);

        //5) 새로운 자료형을 adapt할때 마지막 매개변수만 변환하면 됨
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,datalist);

        //3. ListView 연결 = adapterview 연결
        //ex) ListView는 텍스튜를 여러개 나타내는 형식 임 = 하나의 텍스트뷰에는 (data안에 있는 내용 + 레이아웃)으로 구성된 item 출력
        lv.setAdapter(adapter);

        //4. 이벤트 처리
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //매개변수 설명
                //position? 선택된 item 위치

                //4-1> 선택 데이터 조회
                //Toast.makeText(getApplicationContext(),(position+1)+"번째 데이터 \'"+ data[position]+"\'를 선택하셨습니다.", Toast.LENGTH_LONG).show();

                //4-2> 선택 데이터를 리스트 추가- 복사/붙여넣기
                //ListView 변경, data 배열 변경(배열 길이 정적 한계) -> 즉, 배열을 ArrayList형태로 바꿈
                //Toast.makeText(getApplicationContext(),(position+1)+"번째 데이터 \'"+ datalist.get(position)+"\'를 선택하셨습니다.", Toast.LENGTH_LONG).show();

                //datalist에 선택 데이터를 추가
                //datalist.add(datalist.get(position)+" 복사");
                //ListVIew에도 추가
                //어댑터? datalist와 lv를 연결함
                //즉, 어댑터에 데이터내용 변경된것을 반영해라
                //adapter.notifyDataSetChanged();

                //4-3> 선택 데이터를 리스트뷰에서 삭제
                //datalist.remove(datalist.get(position));
               //adapter.notifyDataSetChanged();

                //4-4> 선택하면 ListDetailActivity 호출
                //ex)  자바 선택시, ListDetailActivity 폼
                //과정명 : 자바
                //설명 : 자바 기본 문법 배웁니다
                //성적 : 90
                //ex) 다른 것 선택시
                //과정명 : 기타 다른것
                //설명 : 정보 없음
                //성적 : 0

                Intent i = new Intent(getApplicationContext(), ListDetailActivity.class);
                i.putExtra("subject", datalist.get(position).toString());
                startActivity(i);
            }
        });

    }
}
















