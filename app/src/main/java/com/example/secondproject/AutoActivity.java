package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;

public class AutoActivity extends AppCompatActivity {

    //멤버변수 선언
    MultiAutoCompleteTextView auto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        auto = findViewById(R.id.auto);
        //이벤트 처리도 할 수 있지만(xxxListener) - 키패트로 입력받은 값을 얻어오기 등
        //자동완성에 관련된 목록을 보여주는 것이 기본
        //1. 자동완성목록 준비 : db
        //하지만, 현재 준비된 db가 없으므로 String[]로 만들어 두기
        //1-1. 직접 자바소스로 만들기
        //String[] array = {"java","jquer","자바","자바스크립트","js","android","andre"};
        //1-2. values/array.xml 파일로부터 가져와서 사용
        //사용방법 : 자바소스에서 리소스파일에 접근하는 방법
        //drawable : R.drawable.google(png,jsp파일)
        //layout : R.layout.sample(xml파일)
        //layout파일에서의 특정 id 뷰 : R.id.아이디명
        //values : 아래와 같은 방법 참고 -> 단, 가지고 올 값이 string인지, array인지를 확인
        Resources resources = getResources();
        String[] array = resources.getStringArray(R.array.autolist);
        //2. Adapter 준비(View와 목록을 연결) : 배열, 레이웃구성, 자동완성뷰
        //Adapter 생성자 매개변수 : (사용할 액티비티 ex, 현재 액티비티, 배열 내부의 요소 1개씩 가져왔을 때 보여줄 모양(레이아웃) ex, 드롭다운리스트 , 배열이름)
        //안드로이드 내장 레이아웃 : ex, LinearLayout(선형 레이아웃)
        //android.R.layout : 안드로이드 내장된 레이아웃 사용
        //R.layout : 내가 만든 레이아웃의 xml파일
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,array); //String값만 연결하겠다는 의미
        //android.R.layout.simple_dropdown_item_1line : 배열의 요소를 하나씩 드롭다운 방식으로 보여줌
        //3.Adapter와 자동완성뷰를 연결
        //뷰 이름 : auto
        //연결할 어댑터 이름 : adapter
        auto.setAdapter(adapter);
        //4. 콤마 분리자 설정
        // java를 찾고 a로 시작할때 a로 시작하는 것을 찾아야하는데 구분자가 없으면 여전히 j로 시작하는 것을 찾게됨
        // 이를 위해 ','로 분리자를 사용하겠다고 설정함
        //MultiAuto.. : 자동완성 시켜줄 것을 여러개 목록 설정
        auto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        //5. 최소 1글자만 작성해도 자동완성되게 설정(설정안하면 2글자가 기본값)
        auto.setThreshold(1);

    }//oncreate end
}//activity end

















