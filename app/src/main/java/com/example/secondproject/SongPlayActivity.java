package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class SongPlayActivity extends AppCompatActivity {
    Button startBtn, stopBtn, pauseBtn, restartBtn;
    TextView state;
    MediaPlayer mp3;
    SeekBar musicbar;
    SimpleDateFormat sdf;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);
        startBtn = findViewById(R.id.startBtn);
        stopBtn = findViewById(R.id.stopBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        restartBtn = findViewById(R.id.restartBtn);
        state = findViewById(R.id.state);
        musicbar = findViewById(R.id.musicbar);

        mp3 = new MediaPlayer();
        mp3 = MediaPlayer.create(getApplicationContext(), R.raw.bts);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdf = new SimpleDateFormat("mm분 ss초"); // SimpleDateFormat은 여러 패키지 안에 있어서 자동 import안될수 있음 -> java.text패키지 import!
                final int playtime = mp3.getDuration();
                mp3.start();
                state.setText("음악재생중 : 총재생시간 = "+sdf.format(playtime)); // mp3.getDuration() ? 음악재생 총시간(ms단위) cf) millisecond = 1000분의 1초

                startBtn.setClickable(false);
                stopBtn.setClickable(true);
                pauseBtn.setClickable(true);
                restartBtn.setClickable(true);

                musicbar.setMax(mp3.getDuration());

                new Thread(){ //음악 재생 현재 위치값과 시크바 변경이 동시에 실행됨
                    public void run(){
                        while(mp3.isPlaying()){
                            musicbar.setProgress(mp3.getCurrentPosition());
                            state.setText(sdf.format(mp3.getCurrentPosition()));
                            try{
                                //0.2초마다
                                Thread.sleep(200);
                            }catch (Exception e){
                            }
                        }
                    }
                }.start();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3.pause();
                state.setText("일시정지");
                current = mp3.getCurrentPosition();
                pauseBtn.setClickable(false);
            }
        });
        pauseBtn.setClickable(false);

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3.seekTo(current);
                restartBtn.setClickable(false);
                pauseBtn.setClickable(true);
            }
        });
        restartBtn.setClickable(false);

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp3.stop();
//                mp3.release();
                state.setText("음악 종료");
                stopBtn.setClickable(false);
                pauseBtn.setClickable(false);
                restartBtn.setClickable(false);
                startBtn.setClickable(true);
//                paused = false;
//                startBtn.setText("음악 재생");
            }
        });
        stopBtn.setClickable(false);

    }
}
