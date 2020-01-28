package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText id;
    EditText pwd;
    Button loginbtn;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = findViewById(R.id.id);
                pwd = findViewById(R.id.pwd);
                result = findViewById(R.id.result);
                result.setText("아이디 "+id.getText()+ "님의 암호는 "+pwd.getText()+"을 입력하셨습니다.");
            }
        });

    }
}
