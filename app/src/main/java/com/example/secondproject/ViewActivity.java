package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

    Button button1;

    Button inputBtn;
    TextView textView;
    EditText editText;

    CheckBox cb1, cb2, cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast = 액티비티 팝업창, 보이는 위치 지정할 수 X - 화면 하단 , 자동으로 사라짐
                // Toast.makeText : Toast 팝업을 만드는 Method
                // getApplicationContext() : 어플리케이션의 Context가 return, 현재 activiy의 context 뿐만 아니라 application의 lifeCycle에 해당하는 Context가 사용
                // android:text="버튼1"을 불러오려면 button1.getText()
                // Toast.LENGTH_SHORT : Toast가 실행되는 길이 설정
                Toast t = Toast.makeText(getApplicationContext(), button1.getText() + "실행", Toast.LENGTH_SHORT);

                // t.show : Toast 팝업 실행 Method
                t.show();

                button1.setText("버튼4");
            }
        });

        inputBtn = findViewById(R.id.inputBtn);
        textView = findViewById(R.id.keyResult);
        editText = findViewById(R.id.key);

        inputBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textView.setText(editText.getText() + " : 입력하셨습니다.");
            }
        });

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);

        // cb1, cb2, cb3의 구현 내용이 동일하다면 무명클래스 보다 객체를 생성하는 것이 좋음 -> Inner Class
        MyListener myListener = new MyListener();

        cb1.setOnCheckedChangeListener(myListener);
        cb2.setOnCheckedChangeListener(myListener);
        cb3.setOnCheckedChangeListener(myListener);

    }

    // Inner Class
    class MyListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(getApplicationContext(), buttonView.getText() + "의 선택상태 = " + isChecked, Toast.LENGTH_SHORT).show();
        }
    }
}