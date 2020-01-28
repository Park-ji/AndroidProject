package com.example.secondproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DB2Activity extends AppCompatActivity {

    EditText name, memnumber, seq;
    Button insertBtn, selectBtn;
    ListView dblist;
    MyDBHelper helper;
    String result="";
    ArrayList<String> cursorlist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db2);

        dblist = findViewById(R.id.dblist);
        seq = findViewById(R.id.seq);
        name = findViewById(R.id.name);
        memnumber = findViewById(R.id.memnumber);
        insertBtn = findViewById(R.id.insertBtn);
        selectBtn = findViewById(R.id.selectBtn);

        helper = new MyDBHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();//select문 실행

        Cursor rs = db.rawQuery("select * from board", null);
        cursorlist = new ArrayList<>();
        result = "결과없음";
        while(rs.moveToNext()){
            result  =  "번호= " + rs.getString(0) + ", 제목 = "+ rs.getString(1) +", 내용 = "+rs.getString(2);
            cursorlist.add(result);
        }
        if(cursorlist.isEmpty()) cursorlist.add(result);

        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1 ,cursorlist);
        dblist.setAdapter(adapter);

        rs.close();
        db.close();//하나 sql실행 끝나면 db닫기

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();
                db.execSQL("insert into board values("+seq.getText()+", '"+name.getText()+"', '"+memnumber.getText()+"')");
                db.close();
                Toast.makeText(getApplicationContext(), seq.getText()+", "+name.getText()+", "+memnumber.getText()+" : 입력 잘 되었음", Toast.LENGTH_SHORT).show();
                //cursorlist.add("그룹명= " + name.getText() + ", 인원수 = "+ memnumber.getText());
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();//select문 실행
                //커서 타입으로 조회하기
                //db.execSQL("select * from groupTBL"); //execSQL : 커서 타입 리턴 X
                Cursor rs = db.rawQuery("select * from board", null);
                cursorlist.clear();
                //두번째 매개변수 null? 조건이 필요 없음을 의미
                result = "결과없음";
                while(rs.moveToNext()){
                    //result +=  "그룹명= " + rs.getString(0) + ", 인원수 = "+ rs.getString(1)+"\n";
                    result  =   "번호= " + rs.getString(0) + ", 제목 = "+ rs.getString(1) +", 내용 = "+rs.getString(2);
                    cursorlist.add(result);
                }
                if(cursorlist.isEmpty()) cursorlist.add(result);

                rs.close();
                db.close();//하나 sql실행 끝나면 db닫기
                adapter.notifyDataSetChanged();
            }
        });

    }//oncreate end

    //inner class : SQLiteOpenHelper 반드시 상속
    class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(Context context){
            super(context, "sample.db",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
          //sample.db에는 테이블 존재(board)
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }//mydbhelper end

}//activity end













