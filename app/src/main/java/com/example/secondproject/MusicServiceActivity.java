package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MusicServiceActivity extends AppCompatActivity {

    Button startBtn, stopBtn;
    Intent intent; //전역 변수 선언? 무명의 내부 클래스 안에서도 사용 가능

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_service);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        intent = new Intent(getApplicationContext(), BackgroundMusicService.class);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
                startBtn.setClickable(false);
                stopBtn.setClickable(true);
                Log.d("액티비티클래스 : ", "startService 호출");
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
                startBtn.setClickable(true);
                stopBtn.setClickable(false);
                Log.d("액티비티클래스 : ", "stopService 호출");
            }
        });
        stopBtn.setClickable(false);
    }
}
















