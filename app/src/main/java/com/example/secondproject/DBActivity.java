package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

import static java.util.Objects.isNull;

public class DBActivity extends AppCompatActivity {
    //실습 : edittext 2개, 입력버튼 1개 생성
    //첫번째 edittext : 그룹이름
    //두번째 edittext : 인원수
    //입력버튼 클릭? insert
    EditText name, memnumber;
    Button insertBtn, selectBtn;
    ListView dblist;
    MyDBHelper helper;
    String result="";
    ArrayList<String> cursorlist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dblist = findViewById(R.id.dblist);
        name = findViewById(R.id.name);
        memnumber = findViewById(R.id.memnumber);
        insertBtn = findViewById(R.id.insertBtn);
        selectBtn = findViewById(R.id.selectBtn);

        //groupdb 데이터베이스 생성, grouptbl 테이블 생성 -> MyDBHelper 생성자 호출 + MYDBHelper : onCreate 오버라이딩
        //생성자 호출시 onCreate 또는 onUpgrade 메서드 중 하나가 호출됨 => 버전에 따라 결정
        helper = new MyDBHelper(this);//Context정보 넘기기(어떤 액티비티가 해당 innerclass(db)를 사용할지 결정)
        //onCreate, onUpgrade메소드는 자동 실행

//        SQLiteDatabase db = helper.getWritableDatabase();//db 열기 + insert, update, delete문 실행
//        //execSQL : sql문을 하나씩 실행하는 메서드
//        //참고 : groupTBL은 그룹이름이 기본키
//        db.execSQL("insert into groupTBL values('방탄', 7)");
//        db.execSQL("insert into groupTBL values('트와이스', 10)");
//        db.execSQL("insert into groupTBL values('슈퍼주니어', 13)");

//        db.close();//하나의 sql실행 끝나면 db닫기
//        Toast.makeText(getApplicationContext(),"입력 잘 되었음", Toast.LENGTH_SHORT).show();


        SQLiteDatabase db = helper.getReadableDatabase();//select문 실행
        //커서 타입으로 조회하기
        //db.execSQL("select * from groupTBL"); //execSQL : 커서 타입 리턴 X
        Cursor rs = db.rawQuery("select * from groupTBL", null);
        cursorlist = new ArrayList<>();
        //두번째 매개변수 null? 조건이 필요 없음을 의미
        result = "결과없음";
        while(rs.moveToNext()){
            //result +=  "그룹명= " + rs.getString(0) + ", 인원수 = "+ rs.getString(1)+"\n";
            result  =  "그룹명= " + rs.getString(0) + ", 인원수 = "+ rs.getString(1);
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
                db.execSQL("insert into groupTBL values('"+name.getText()+"', "+memnumber.getText()+")");
                db.close();
                Toast.makeText(getApplicationContext(),name.getText()+", "+memnumber.getText()+" : 입력 잘 되었음", Toast.LENGTH_SHORT).show();
                //cursorlist.add("그룹명= " + name.getText() + ", 인원수 = "+ memnumber.getText());
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();//select문 실행
                //커서 타입으로 조회하기
                //db.execSQL("select * from groupTBL"); //execSQL : 커서 타입 리턴 X
                Cursor rs = db.rawQuery("select * from groupTBL", null);
                cursorlist.clear();
                //두번째 매개변수 null? 조건이 필요 없음을 의미
                result = "결과없음";
                while(rs.moveToNext()){
                    //result +=  "그룹명= " + rs.getString(0) + ", 인원수 = "+ rs.getString(1)+"\n";
                    result  =  "그룹명= " + rs.getString(0) + ", 인원수 = "+ rs.getString(1);
                    cursorlist.add(result);
                }
                if(cursorlist.isEmpty()) cursorlist.add(result);

                rs.close();
                db.close();//하나 sql실행 끝나면 db닫기
                adapter.notifyDataSetChanged();

                //if(cursorlist.contains("결과없음") && cursorlist.size()>1) cursorlist.remove(0);
               // adapter.notifyDataSetChanged();
            }
        });


        //Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
    }//oncreate end

    //460p
    //inner class : SQLiteOpenHelper 반드시 상속
    class MyDBHelper extends SQLiteOpenHelper {
        //필요? 생성자, onCreate, 필요한 메서드 오버라이딩

        //1. 생성자 : db이용에 도움을 줌 - 중요기능? db명 생성
        //따라서, db명 부여하고 생성(ex, sample.db 생성 과정)
        public MyDBHelper(Context context){ //context? 어떤 액티비티를 사용할지 액티비티 명시
            //super(context, "group.db",null,1);//나를 상속한 클래스 생성자 호출(사용 액티비티, db명, null, 1(버전))
            super(context, "group.db",null,2);//이전에 실행한 버전과 다른 버전? onUpgrade메소드 실행
        }

        //2. onCreate 오버라이딩  : 중요기능? 테이블 생성(ex, board테이블 생성 과정)
        //핸드폰에 db가 있는지 없는지 모르기 때문에 미리 db생성해주기!
        @Override
        public void onCreate(SQLiteDatabase db) {
            //테이블 생성
            //자동 호출 MyDBHelper 생성자 4번째 매개변수? db버전
            //db버전이 1이면? 최초의 db만듦 = onCreate메서드만 실행됨
            db.execSQL("create table groupTBL (name text primary key, memnumber integer)");
        }

        //3. onUpgrade 오버라이딩 : 중요기능? 테이블 구조 변경
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //테이블 구조 변경? 테이블 삭제 하고 새로운 구조로 테이블 생성
            //자동 호출 MyDBHelper 생성자 4번째 매개변수 db버전 2이상이면? 삭제되고 새로운 테이블 생성
            db.execSQL("drop table if exists groupTBL ");
            onCreate(db);
        }
    }//mydbhelper end

}//activity end













