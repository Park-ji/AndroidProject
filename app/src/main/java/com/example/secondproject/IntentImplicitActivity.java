package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class IntentImplicitActivity extends AppCompatActivity {

    Button callBtn, homePageOpenBtn, googleMapBtn;
    Button googleSearchBtn, smsBtn, settingBtn;
    Button camerBtn, shareBtn, playBtn;
    Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_implicit);

        callBtn = findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tel:로 시작하면 전화번호로 인식하게 하려면?
                //jsp : "{"a" : "aaaa", , , , }"
                //javascript : JSON.parse("{"a" : "aaaa", , , , }");
                //jsp에서 생성한 String이 JSON 형식인지 javascript에서 확인하는 것 처럼
                //Uri.parse에서 tel의 형식을 확인
                String tel = "tel:010-1234-5678";
                Uri uri = Uri.parse(tel);

                //내장 앱, 사용자 앱 호출은 Intent 사용

                //Intent.ACTION_DIAL = 전화걸기화면(다이얼화면) 내장 앱
                //uri = 앞의 동작할 하기 위한 데이터
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                //내장 앱은 result 없음 -> startActivityForResult 사용 X
                startActivity(intent);
            }
        });

        homePageOpenBtn = findViewById(R.id.homepageOpenBtn);
        homePageOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //내장앱
                //브라우저
                String http = "http://www.kitri.re.kr";
                Uri uri = Uri.parse(http);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);//ACTION_VIEW : 해당 url을 보여줌(홈페이지나 맵이나 같은 동작)
                startActivity(i);
            }
        });

        googleMapBtn = findViewById(R.id.googleMaplBtn);
        googleMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //맵은 주소값을 위도와 경도, 고도로 이뤄짐 - 고도는 사용안할수도 O
                //구글맵 동작(위도, 경도)- http://maps.google.com/maps?q=37.485325, 126.898796
                //위도, 경도 찾기?
                //1. 직접 찾기 : 구글맵에서 원하는 주소 -> 오른쪽 클릭 -> 이곳이 궁금해요
                //2. 안드로이드가 자동으로 찾기
                //아래의 예제? 1번 방법
                String http ="http://maps.google.com/maps?q=37.485325, 126.898796";
                Uri uri = Uri.parse(http);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);//ACTION_VIEW : 해당 url을 보여줌(홈페이지나 맵이나 같은 동작)
                startActivity(i);
            }
        });

        googleSearchBtn= findViewById(R.id.googleSearchBtn);
        googleSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uri는 필요 X
                //이유? 어차피 검색할 uri는 구글로 정해져 있기 때문에
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);//검색앱
                //검색할 데이터 설정
                //i.putExtra("word","안드로이드"); -> X
                //하지만, 내장앱을 부르고 있기 때문에 변수명을 내장앱에 검색어로 정하고 있는 이름으로 정해야함
                // : SearchManager.QUERY (내장앱이 정한 이름을 제공하는 메소드 : searchmanager메소드)
                i.putExtra(SearchManager.QUERY,"안드로이드");
                startActivity(i);
            }
        });

        //추가할 내장앱 테스트

        //1. 문자
        smsBtn= findViewById(R.id.smsBtn);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문자 보내기에 필요한 요소 : 전화번호, 메세지
                //1) 전화번호 : 숫자외의 구분자는 아무거나 사용해도 가능 ex)-,/ ...
                String sms = "smsto:010/1234/5677";
                //ex) 회사에서 대량의 메세지 보내는 경우 : String 배열로 구성해서 보내기
                Uri uri = Uri.parse(sms); //파싱할때 구분자 알아서 구분함
                //uri의미 : 문자 보낼 대상
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                //2) 메세지 : 변수명 'sms_body' 또는 Intent.EXTRA_TEXT 사용
                //Intent.EXTRA_TEXT : 문자의 메세지를 의미
                i.putExtra(Intent.EXTRA_TEXT,"안녕하세요 안드로이드");
                startActivity(i);
            }
        });

        //2. 세팅
        settingBtn= findViewById(R.id.settingBtn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //안드로이드는 기본적으로 어떤 핸드폰이든 전화, 문자 기능은 O
                //세팅앱은 기본적으로 제공되는 기능 X = Settings 클래스 사용
                //세팅화면을 볼때 Data는 필요 X
                Intent i = new Intent(Settings.ACTION_SETTINGS);
                //ACTION_SETTINGS : 세팅 초기화면
                //상세 초기 화면 : ACTION_xxx_SETTINGS
                startActivity(i);
            }
        });

        //3. 카메라
        camerBtn= findViewById(R.id.cameraBtn);
        camerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사진을 찍으려고 카메라를 열 때 Data는 필요 X
                //cf) 사진 전송하려면 Data가 필요하겠쥬~
                //카메라 앱 = MediaStore클래스 사용
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });

        //4. 텍스트 복사 후 -> 공유 -> 공유할 곳 결정(이메일, 카카오통 등 공유 할수 있는 앱 중)
        shareBtn= findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //문자 : ACTION_SENDTO
                //공유앱 : ACTION_SEND
                Intent i = new Intent(Intent.ACTION_SEND);
                //어떤 공유앱이 선택될지 모름 -> EXTRA_xxx로 지정하고 싶은 것 다 지정
                //데이터 타입 지정 -> 어떤 공유 앱을 띄울지 결정
                i.setType("text/plain"); //텍스트 파일 형식
                //제목이 필요하면?
                i.putExtra(Intent.EXTRA_SUBJECT, "제목");
                //내용이 필요하면?
                i.putExtra(Intent.EXTRA_TEXT,"내용");
                //후보군 중에 이메일이 필요한 앱이 선택되면?
                i.putExtra(Intent.EXTRA_EMAIL,"KITRI@a.com");
                //즉, 위에 putExtra로 지정된 것은 쓰일 수도 안 쓰일 수도 있음
                startActivity(i);
            }
        });

        //5. 구글플레이 스토어
        playBtn= findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //"" 형식 : "종류:xxxxx"
                //종류 : tel, market, http...
                String market  = "market://search?q=helloworld";
                Uri uri = Uri.parse(market);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(i);
            }
        });

        //6. 현재 앱 종료
        closeBtn= findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//현재 액티비티 종료
            }
        });

    }//oncreate end
}//acitivity end























