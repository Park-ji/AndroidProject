package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileInputActivity extends AppCompatActivity {

    Button btn;
    TextView memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_input);

        //실습내용 : 메모장 보여줘 버튼 클릭 -> TextView 내부에 파일내용 출력
        // /data/data/패키지명/files/file.txt 파일 내용 입력

        btn = findViewById(R.id.btn);
        memo = findViewById(R.id.memo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fis = openFileInput("file.txt");
                    //byte[] file_in = new byte[4000];
                    //입력되어진 글자수가 다르므로 입력글자만큼 받는것이 제일 좋음
                    //배열길이는 정적길이로 불변
                    //입력글자만큼 받으려면? fis.availabe메소드 사용
                    //fis.available : 파일 총 크기가 byte단위로 리턴
                    int len = fis.available();
                    byte[] file_in = new byte[fis.available()];
                    fis.read(file_in);//반복문 쓰지 않아도 자동으로 파일의 내용을 읽어옴
                    fis.close();
                    String s_file_in = new String(file_in); //바이트배열로 저장된 것을 String으로 만듦
                    /*Toast.makeText(getApplicationContext(),
                            s_file_in + "\n내용을 읽었습니다.", Toast.LENGTH_LONG).show();*/
                    memo.setText(s_file_in);
                }catch(IOException e){ Toast.makeText(getApplicationContext(),
                        "오류 발생 원인은" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        

    }
}




















