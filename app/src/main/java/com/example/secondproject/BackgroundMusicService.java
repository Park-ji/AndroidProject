package com.example.secondproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class BackgroundMusicService extends Service {

    MediaPlayer mp3;

    public BackgroundMusicService() {
        //컴파일 관련 창 : Build
        //컴파일 완료 후, 압축 해제하면서 실행할때? 실행 결과 확인 창 : run, Logcat
        //현재 액티비티 결과창(에러 상관없이) : run
        Log.d("서비스클래스 : ", "생성자호출");

    }

    @Override
    public IBinder onBind(Intent intent) {
        //구현 종류
        //1. 액티비티와 독립적인 백그라운드 작업 = 액티비티에 줄 결과 X = return null
        //2. 액티비티와 상화작용하는 백그라운드 작업 = 액티비티에 줄 결과 O = 해당 리턴값 리턴 하면 됨
        //호출 방법 : 액티비티에서 startService가 아니라 bindService로 호출
        Log.d("서비스클래스 : ", "onBind 호출");
        return null;
    }

    @Override
    public void onCreate() {
        //자기 자신의 super클래스의 똑같은 메소드 호출 이유? 해당 내용도 실행하고 내가 정의한 내용도 실행해야되서
        super.onCreate();
        Log.d("서비스클래스 : ", "onCreate 호출");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("서비스클래스 : ", "onStartCommand 호출");
        //음악 연주 기능 구현
        //사용 resource? R.raw.song1 또는 /sdcard/song1.mp3
        mp3 = new MediaPlayer();
        //mp3.setDataSource(...); : sdcard안에 있는 mp3파일 사용시
        mp3 = MediaPlayer.create(this, R.raw.song1);
        mp3.setLooping(true);//선택해놓은 음악을 계속해서 재생할때 사용(1번만 연주하는 것이 아니라 반복)
        mp3.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //음악 중지
        super.onDestroy();
        mp3.stop();
        Log.d("서비스클래스 : ", "onDestroy 호출");
    }
}



























