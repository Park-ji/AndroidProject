package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {

    DatePicker date;
    EditText input;
    Button btn;
    String filename;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public void check(String filename){

         /*File f = new File("a.txt");
                    //File 객체는 입출력관련 X
                    f.getAbsolutePath();//'a.txt'의 경로 확인
                    f.isDirectory();//파일인지 아닌지 확인
                    f.exists();//존재 여부 확인*/

        //파일 저장 경로
        File f = new File("/data/data/com.example.secondproject/files");
        //파일 목록 조회 - String배열
        String[] list = f.list();
        for(int i=0;i<list.length;i++) {
            if(list[i].equals(filename)){
                try {
                    FileInputStream fis = openFileInput(list[i]);
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    fis.close();
                    String s_b = new String(b);
                    input.setText(s_b);
                    break;
                }catch(IOException e){
                    Toast.makeText(getApplicationContext(),
                            "오류 발생 원인은" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            else{
                input.setText("");
            }
        } //파일 있으면 내용 보이기

    } //check메소드 end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        date = findViewById(R.id.date);
        input = findViewById(R.id.input);
        btn = findViewById(R.id.btn);

        Date currentTime = Calendar.getInstance().getTime();
        //String filename = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime)+".txt";
        filename = simpleDateFormat.format(currentTime)+".txt";
        check(filename);


        //버전 만족하면 실행 - api 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    //filename = String.valueOf(year) + String.valueOf(monthOfYear+1) + String.valueOf(dayOfMnth) +".txt";
                    //filename = year + "" + (monthOfYear+1) + "" + dayOfMonth + ".txt";
                    Calendar newTime = Calendar.getInstance();
                    newTime.set(year,monthOfYear,dayOfMonth);
                    filename = simpleDateFormat.format(newTime.getTime())+".txt";
                    check(filename);
                }
            });
        }//datechangedlistner end



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sample = input.getText().toString();
                try {
                    FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND);
                    fos.write(sample.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(),
                            "정상 수행:" + filename + "파일에 저장 완료", Toast.LENGTH_LONG).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(),
                            "오류 발생 원인은" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });//btnclick end

        //일정 삭제 버튼? new File("2020-01-02.txt").delete() 메소드 이용시 삭제 가능능

    }//oncreate end
}//activity end
