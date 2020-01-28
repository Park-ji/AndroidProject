package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleActivity extends AppCompatActivity {

    TextView result;
    int cnt = 1;

    //on으로 시작되는 메소드는? 부모클래스에서 실행해야 할 게 있다면 먼저 실행시키고 진행해야함 : super

    //Activity 실행시 최초 호출 메소드? onCreate
    //생명주기?
    //1) 시작 : onCreate(최초 한번 실행) -> onStart -> onResume
    //2) 메인 액티비티 실행
    //2-1) 다른 액티비티 요청 : onPause -> onStop-> 다른 액티비티 사용 및 종료 -> onRestart -> onStart -> onResume -> 2번
    //2-2) 메인 액티비티 종료 : onPause -> onStop -> onDestory(메모리에서 사라질때(gc실행시) 최종 1번)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        result = findViewById(R.id.result);
        result.append(cnt++ + ". onCreate 실행중\n");
        //Build : 컴파일 에러 창
        //Run, Logcat : 실행결과 창
        //System.out.println();  //안드로이드스튜디오  개발자 창 중 실행결과 창
        Log.d("라이프사이클:========>", cnt + ".onCreate 실행중"); //로그의 종류? 일반적인 로그 메시지(.d) , 에러 로그 메시지(.e).....

    }

    @Override
    protected void onStart() {
        super.onStart();
        result.append(cnt++ + ". onStart 실행중\n");
        Log.d("라이프사이클:========>", cnt + ".onStart 실행중");
    }

    @Override
    protected void onResume() {
        super.onResume();
        result.append(cnt++ + ". onResume 실행중\n");
        Log.d("라이프사이클:========>", cnt + ".onResume 실행중");
    }

    @Override
    protected void onStop() {
        super.onStop();
        result.append(cnt++ + ". onStop 실행중\n");
        Log.d("라이프사이클:========>", cnt + ".onStop 실행중");
    }

    @Override
    protected void onPause() {
        super.onPause();
        result.append(cnt++ + ". onPause 실행중\n");
        Log.d("라이프사이클:========>", cnt + ".onPause 실행중");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        result.append(cnt++ + ". onDestroy 실행중\n");
        Log.d("라이프사이클:========>", cnt + ".onDestroy 실행중");
        //현재 액티비티가 메모리에서 사라질 때 자동 호출
        //메모리 사라지는 시기는 안드로이드 시스템이 정함
        //안드로이드 메모리 모자라는 시점에 안드로이드 시스템이 가장 오래된 앱부터 자동 삭제
    }
}
