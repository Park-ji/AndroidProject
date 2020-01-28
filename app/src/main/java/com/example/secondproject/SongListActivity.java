package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//액티비티만 사용 : /data/data/패키지명/files/*.mp3
//외장메모리 출력 경로 : /storage/emulated/0
//외장메모리 실제 경로 : /sdcard
// /sdcard/*.mp3 파일 ListView 보여준다(외장하드)

public class SongListActivity extends AppCompatActivity {
    ListView lv;
    Button start, stop, pause;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MediaPlayer mediaPlayer;
    String path;
    String selectedMP3;
    boolean paused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        lv = findViewById(R.id.lv);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);

        //외장메모리 경로 확인
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();

        //핸드폰 내장메모리 테스트
        // path = "/data/data/com.example.secondproject/";

        //외장메모리 권한 확인
        ActivityCompat.requestPermissions(
                this,
                //사용자에게 요청할 권한들 요청 - String 배열
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                MODE_PRIVATE);

        //Cf) path 경로 아래의 파일과 디렉토리 목록들 가지고오기
        //1) 파일 객체
        File dir = new File(path);
        //2) 파일 목록
        File[] flist = dir.listFiles(); //path하위의 디렉토리 파일 목록 다 가지고 옴
        //2. 확장자가 mp3인 것 찾음
        //3. ArrayList<String>에 저장
        list = new ArrayList<String>();
        for (int i = 0; i < flist.length; i++) {
            File file = flist[i];
            if (file.isFile()) {
                String fname = file.getName();
                int len = fname.length();
                if (fname.substring(len - 3).equals("mp3")) list.add(fname);
            }
        }
        //4. Adapter 생성
        //5. 라디오버튼 모양 어댑터
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(adapter);
        lv.setItemChecked(0, true);
        selectedMP3 = list.get(0);

        //5. Event  처리
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position) + " 선택", Toast.LENGTH_SHORT).show();
                selectedMP3 = list.get(position);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " start", Toast.LENGTH_SHORT).show();
                try{
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(path+selectedMP3);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    start.setClickable(false);
                    stop.setClickable(true);
                    pause.setClickable(true);
                }catch (IOException e){ }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " pause", Toast.LENGTH_SHORT).show();
                if(paused == false){
                    mediaPlayer.pause();
                    paused = true;
                }
                else{
                    mediaPlayer.start();
                    paused = false;
                }
            }
        });
        pause.setClickable(false);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " stop", Toast.LENGTH_SHORT).show();
                mediaPlayer.stop();
                mediaPlayer.reset();
                start.setClickable(true);
                pause.setClickable(false);
                stop.setClickable(false);
            }
        });
        stop.setClickable(false);
    }
}


















