package com.example.secondproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView web;
    EditText url;
    Button movebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        web = findViewById(R.id.web);
        //브라우저가 뜨면 자동으로(최초로) 구글로 접속하게 설정
       // web.loadUrl("http://www.google.com");
        //webserver, dbserver, 안드로이드 개발 컴퓨터
        //네트워크 통신 - avd(127.0.0.1)실행
        //avd는 별도의 기계로 취급해서 안드로이드 스튜디오와 통신하는 절차로 생각함
        //그래서, 127.0.0.1이나 localhost로 사용하면 안됨 실제 ip주소를 줘야함(내 컴퓨터에서 avd실행해도 avd는 내컴퓨터에서 실행한다고 생각 x)
        web.loadUrl("http://192.168.15.40:8081/jquery/event1.html");
        //웹브라우저 불필요하지만 안드로이드 웹뷰에서는 필요 - 자바스크립트
        //자바스크립트 사용가능하게 설정 변경(기본적으로 안되도록 설정되어있음)
        WebSettings settings = web.getSettings(); //웹 관련 설정
        settings.setJavaScriptEnabled(true);

        //alert, confirm, prompt : 웹브라우저 앞 팝업창 모양으로 나타난 모양
        //웹뷰는 데스크 탑에서 사용하는 팝업창을 기본적으로 가지고 오지는 못함
        //즉, alert설정이 가능하도록 설정 해줘야함
        web.setWebChromeClient(new WebChromeClient(){ //alert, confirm, prompt도 오버라이드해서 사
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                //안드로이드에서 alert를 대신할 것으로 Toast창 사용

/*                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                 //getApplicationContext() : 현재 어플리케이션에서 사용
                //message : 서버에서 보내는 alert창의 들어가는 메세지를 받은 매개변수
                //모달형태 : 앞의 팝업창이 뜨면 뒤의 화면이 보이지 않고 어떤 작업을 할 수 없는 형태
                //즉, alert창의 '확인'버튼을 누르라고 해줘야함*/


                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
                builder.setMessage(message);
                builder.setPositiveButton("확인", new AlertDialog.OnClickListener(){ //WebViewActivity.java에서 응용하기
                    //별도의 클래스의 별도의 메소드
                    //innerclass에서 outerclass의 변수 접근 못하므로 final
                    //언제 닫힐지 모름? result 변수가 계속 필요할 수 있으므로 final => 메모리에 계속 남아야함
                    //즉, onJsAlert끝나도 AlertDialog 팝업창 계속 실행중

                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        result.confirm();
                        //메소드 종료 이후 변수 지속 : final
                    }
                });

                builder.show();

                //result 매개변수 : 서버에 보내는 결과
                //result.confirm();//확인 버튼을 누르는 동작 -> 웹 서버로 알러트 확인 버튼 클릭했으니 팝업을 닫으라는 의미
                return true;//alert창을 닫히면 그 다음 후속작업을 진행하라는 의미
            }
        });

        movebtn = findViewById(R.id.movebtn);
        url = findViewById(R.id.url);

        movebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //url 입력 내용을 받아서 webview에 전달
                //url.getText()는 String이 아니라 CharSequence를 반환함
                web.loadUrl(url.getText().toString());
            }
        });

    }//oncreate end
}//activity end
