package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputActivity extends AppCompatActivity {

    EditText input;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_output);

        //실습 내용 : EditText 문자열 입력 -> Button 클릭 -> file.txt 파일 저장
        //저장 경로 : /data/data/패키지명/files/file.txt

        input = findViewById(R.id.input);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sample = input.getText().toString();
                try {
                    //FileOutputStream fos = openFileOutput("file.txt", Context.MODE_PRIVATE);
                    FileOutputStream fos = openFileOutput("file.txt", Context.MODE_APPEND);
                    //파일 이름 부여시? 자동으로 저장경로 생성
                    //해당 파일 존재시 새로운 내용으로 갱신? Context.MODE_PRIVATE
                    //해당 파일 존재시 새로운 내용을 덧붙임? Context.MODE_APPEND
                    //String sample = "안드로이드 파일 출력 연습중입니다.";
                    fos.write(sample.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(),
                            "정상 수행: file.txt 파일에 저장 완료", Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    //처리하는 곳? Activity : 즉, 안드로이드 환경
                    //e? IOException
                    //e.getMessage? 오류 메세지
                    Toast.makeText(getApplicationContext(),
                            "오류 발생 원인은" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
