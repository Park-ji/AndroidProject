package com.example.secondproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    LinearLayout baselayout;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);//layout inflate
        baselayout = findViewById(R.id.baselayout);//레이아웃 인플레이트 된후 사용 가능
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                //현재 액티비티? MenuActivity
                //this? btn
                builder.setTitle("제목");
                builder.setMessage("문자열내용");
                builder.setIcon(R.drawable.api30);
                builder.setPositiveButton("확인",
                        new AlertDialog.OnClickListener(){ //WebViewActivity.java에서 응용하기
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                Toast.makeText(MenuActivity.this, "확인버튼 클릭하셨으니 삭제 진행합니다.", Toast.LENGTH_LONG).show();
                            }
                        });
                //확인 버튼 생성(버튼 레이블, 리스너 생성)
                //listener? 확인 버튼 눌렀을 때 이벤트 처리
                builder.setNegativeButton("취소", null); //취소 버튼 생성
                builder.show();
            }
        });
    }

    //메뉴 정의 : res/menu/*.xml 정의

    //메뉴 생성 : AppCompatActivity에서 오버라이드해서 사용
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflater? 풍선을 부풀리다
        //안드로이드는 정의해놓은 파일로 화면을 구성하는 과정을 풍선을 부풀리는 과정으로 생각함
        //레이아웃은? setContent..
        //메뉴는? getMenu.. -> 메뉴 부풀리기, 메뉴 정의를 액티비티에 표현함
        //메뉴가 변경되도 다 적용 가능
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);//해당 파일을 메뉴로 구성함, 현재 액티비티의 menu로 써라(여기서의 menu는 매개변수로 받은 menu)
        return  true;
        //필요한 이유? 후속적으로 처리할 다음 메소드 호출
        //false로 하면? 다음 메소드를 넘어가지 못함
    }

    //메뉴 이벤트 처리
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //메뉴중 하나를 선택하면 자동 호출
        switch (item.getItemId()){
            //자바에는 Color라는 클래스 존재 = Color.RED
            //리소스파일로 가지고 오려면 = getResources().getColor(R.color.red)
            case R.id.itemRed : baselayout.setBackgroundColor(Color.RED); break;
            case R.id.itemBlue : baselayout.setBackgroundColor(Color.BLUE); break;
            case R.id.itemGreen : baselayout.setBackgroundColor(Color.GREEN); break;
            case R.id.btntext : Resources r = getResources(); btn.setTextColor(r.getColor(R.color.blue)); break;
            case R.id.btnbg : btn.setBackgroundColor(getResources().getColor(R.color.green));break;
        }
        return false;
    }
}
















