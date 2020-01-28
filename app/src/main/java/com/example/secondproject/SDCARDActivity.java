package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class SDCARDActivity extends AppCompatActivity {

    TextView result;
    Button newdir;
    Button deldir;

    //외장메모리 저장 경로 - 전역 또는 final선언하기(모든 메소드에서 사용하기 위해서)
    String sdcard;
    File mytest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        result = findViewById(R.id.result);
        newdir = findViewById(R.id.newdir);
        deldir = findViewById(R.id.deldir);

        //외장 메모리에 접근전 - 사용자에게 외장메모리 사용 요청
        ActivityCompat.requestPermissions(
                this,
                //사용자에게 요청할 권한들 요청 - String 배열
                new String[]{ android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        result.setText(sdcard);

        newdir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //외장메모리내부 하위폴더 생성
                //폴더 생성 역할? File 객체
                //실제 생성 역할? mkdir()메소드
                mytest = new File(sdcard+"/mytest");
                mytest.mkdir();
                result.setText("디렉토리 생성");
            }
        });//폴더생성 버튼 이벤트 end

        deldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytest.delete();//디렉토리 삭제
                result.setText("디렉토리 삭제");
            }
        });//폴더삭제 버튼 이벤트 end


    }//oncreate end
}//acitivity end

























