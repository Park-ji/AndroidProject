package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MemberIDIntentActivity extends AppCompatActivity {

    EditText loginId;
    Button searchbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_idintent);
        loginId = findViewById(R.id.loginId);
        searchbtn = findViewById(R.id.searchbtn);

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemberInfoIntentActivity.class);
                intent.putExtra("id", loginId.getText().toString());
                startActivity(intent);
            }
        });
    }
}
