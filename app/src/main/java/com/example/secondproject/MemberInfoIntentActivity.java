package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MemberInfoIntentActivity extends AppCompatActivity {
    TextView userId;
    TextView userName;
    TextView userAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info_intent);

        HashMap<String, String> memberMap = new HashMap<>();
        memberMap.put("kitri","이길동,23");
        memberMap.put("multi","김삼성,33");
        memberMap.put("oracle","박사원,21");
        memberMap.put("java","이학생,29");
        memberMap.put("spring","최봄,33");

        userId = findViewById(R.id.userId);
        userName = findViewById(R.id.userName);
        userAge = findViewById(R.id.userAge);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        if(memberMap.containsKey(id)){
            String[] val = memberMap.get(id).split(",");
            userId.setText(userId.getText() + id);
            userName.setText(userName.getText() + val[0]);
            userAge.setText(userAge.getText() + val[1]);
        }
        else{
            userId.setText(userId.getText() + id);
            userName.setText(userName.getText() + "무");
            userAge.setText(userAge.getText() + "0");
        }




    }
}
