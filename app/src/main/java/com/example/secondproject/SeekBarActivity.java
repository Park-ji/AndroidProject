package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {
    TextView progress;
    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        progress = findViewById(R.id.progress);
        seekbar = findViewById(R.id.seekbar);

        //가로 막대로 진행상태를 표시하는 뷰? SeekBar
        //seekbar 범위? 1~100
        //즉, 별도로 setMax()메소드를 이용해 Max를 지정하지 않으면 최댓값은? 100
//        seekbar.setProgress(50);
//        progress.setText(seekbar.getProgress()+"% 진행되었습니다.");

        //cf) 안드로이드 정책
        //안드로이드가 화면을 만들때  = 화면에 view를 만들때 = user interface를 만들때 = ui를 만들때
        //정책? ui를 변경할때는 반드시 thread를 사용해야함
        //즉, 아래의 코드(1)로는 변화를 확인할 수 없음
        //(1)
//        for(int i=0; i<= seekbar.getMax();i+=2){
//            seekbar.setProgress(i);//seekbar의 포인터
//            progress.setText(seekbar.getProgress()+"% 진행되었습니다.");
//            //ex) 0.1초마다 변환된 상태를 표현하고 싶을때?
//            //매개변수? ms단위
//            //sleep? 0.1초 delay 후에 다시 반복문 메소드 실행
//            try {
//                Thread.sleep(100);
//            }catch(Exception e){}
//        }

        //(2) thread를 이용해서 만든 ui
        //thread? 동시 실행 가능
        //1> thread의 개념
        // thread클래스를 상속받은 클래스 선언  : run 메소드안에 있는 내용 동시 실행 가능
//        class A extends  Thread{
//            public void run(){
//
//            }
//        }
//        A a1 = new A();
//        a1.start();
//
        //2> thread상속 받으면서 객체 생성 = 무명 객체 만들기
        new Thread(){
            public void run() {
                for (int i = 0; i <= seekbar.getMax(); i += 2) {
                    seekbar.setProgress(i);//seekbar의 포인터
                    progress.setText(seekbar.getProgress() + "% 진행되었습니다.");
                    //ex) 0.1초마다 변환된 상태를 표현하고 싶을때?
                    //매개변수? ms단위
                    //sleep? 0.1초 delay 후에 다시 반복문 메소드 실행
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();

        } //oncreate end
    } //activity end













