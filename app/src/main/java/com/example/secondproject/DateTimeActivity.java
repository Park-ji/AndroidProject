package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

public class DateTimeActivity extends AppCompatActivity {
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);
        tp = findViewById(R.id.time);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            //선택한 시간은 hourOfDay(시), minute(분)으로 넘어옴
                //Toast는 띄웠다 사라지는 팝업창으로 띄워줄 시간을 지정함(LENGTH_LONG : 약 5초)
                Toast.makeText(getApplicationContext(),"선택한 시간은"+ hourOfDay + "시"+minute+"분입니다.", Toast.LENGTH_LONG ).show();
            }
        });
    }//oncreate end
}//activity class end
