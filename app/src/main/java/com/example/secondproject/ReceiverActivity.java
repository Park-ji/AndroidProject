package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Date;

public class ReceiverActivity extends AppCompatActivity {
    ImageView ivBattery;
    EditText edtBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("배터리 상태 체크");

        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);
        Log.d("배터리액티비티 ", "onCreate");

        //권한 획득 문의
        // 1> manifest에 권한 요청 설정이 있어야함
        // 2> 사용자에게 권한 요청
        int SMS_PERMISSION_REQ_CODE_SUBMIT = 100;
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ReceiverActivity.this, new String[]{Manifest.permission.RECEIVE_SMS},
                    SMS_PERMISSION_REQ_CODE_SUBMIT);
        }

    }

    //BroadcastReceiver 생성
    //1. 클래스 상속
//    class A extends BroadcastReceiver{
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//        }
//    }
//    A a1 = new A();

    //2. 상속받는 무명의 클래스로 생성 : 생성하면서 객체까지 만들기
    BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("배터리리시버 ", "onReceive");
            Log.d("리시버 ", intent.getAction());
            String action = intent.getAction();
            ////test 1 : 배터리의 상태 변화, 배터리의 충전 여부를 발생시, 받는 메소드
//            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
//                //이벤트 발생 시점의 배터리 용량 가져오기? BatterManager.EXTRA_LEVEL
//                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
//                edtBattery.setText("현재 충전량 : " + remain + "\n");
//                Log.d("배터리리시버 ", "onReceive-" + remain);
//                if (remain >= 90)
//                    ivBattery.setImageResource(R.drawable.battery_100);
//                else if (remain >= 70)
//                    ivBattery.setImageResource(R.drawable.battery_80);
//                else if (remain >= 50)
//                    ivBattery.setImageResource(R.drawable.battery_60);
//                else if (remain >= 10)
//                    ivBattery.setImageResource(R.drawable.battery_20);
//                else
//                    ivBattery.setImageResource(R.drawable.battery_0);
//                //이벤트 발생 시점의 배터리 충전상태(전원공급 여부) 가져오기? BatteryManager.EXTRA_PLUGGED
//                //상태 종류 : 연결X, 어댑터로 충전중인지, USB로 충전중인지
//                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
//                switch (plug) {
//                    case 0:
//                        edtBattery.append("전원 연결 : 안됨");
//                        break;
//                    case BatteryManager.BATTERY_PLUGGED_AC:
//                        edtBattery.append("전원 연결 : 어댑터 연결됨");
//                        break;
//                    case BatteryManager.BATTERY_PLUGGED_USB:
//                        edtBattery.append("전원 연결 : USB 연결됨");
//                        break;
//                }
//            }

            //test 2 : 문자 수신 알림 받기
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Log.d("문자리시버 ", "onReceive");
                edtBattery.append("문자왔어요");
            }

            //문자 수신시 문자내용(발신번호, 문자내용, 수신시간) 뽑기
            //SMS문자의 내용을 뽑아내는 정형화된 코드(110~123)
            //정형화된 코드. 그냥 가져다 쓰면 된다.
            Bundle bundle = intent.getExtras();
            Object[] objs = (Object[]) bundle.get("pdus");//pdus? 메세지 형식이 넘어올때 메세지의 형태를 의미 -> 참고
            SmsMessage[] messages = new SmsMessage[objs.length];

            //문자내용 분리해서 {발신번호/문자내용/수신시간}로 parsing해서 messages에 저장
            //여러개 문자가 올 수 있으니까  messages 배열 생성
            for (int i = 0; i < objs.length; i++) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    String format = bundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
                }
            }

            if (messages.length > 0) {
                // 문자메세지에서 송신자와 관련된 내용을 뽑아낸다.
                String sender = messages[0].getOriginatingAddress();
                Log.d("문자리시버 ", "sender: " + sender);

                // 문자메세지 내용 추출
                String contents = messages[0].getMessageBody().toString();
                Log.d("문자리시버 ", "contents: " + contents);

                // 수신 날짜/시간 데이터 추출
                Date receivedDate = new Date(messages[0].getTimestampMillis());
                //ms메서드를 date매개변수로 넘기면? '년도 월일 시분초' 형식으로 반환
                Log.d("문자리시버 ", "received date: " + receivedDate);

                //Activity만 Activity를 호출할 수 있는 것이 X
                //예제 : BroadcastReceiver가 문자앱 Activity 호출
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + sender)));
            }
        }
    };

@Override
protected void onPause(){
        super.onPause();
        unregisterReceiver(br);
        Log.d("배터리액티비티 ","onPause");
        }

@Override
protected void onResume(){
        super.onResume();
        //1. intentFilter 생성
        IntentFilter iFilter=new IntentFilter();
        //2.  iFilter를 받는 쪽에서 체크할 동작 정의
        //test1 : 배터리 변화 체크해서 알려줌(=braodcast)
        //iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        /*<uses-permission android:name="android.permission.SEND_SMS" />
        <uses-permission android:name="android.permission.RECEIVE_SMS" />
        <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        */
        //test2 : 문자 수신 시스템 이벤트 발생시 broadcast
        iFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        //3. intent와 broadcastreceiver상속받은 receiver 등록
        registerReceiver(br,iFilter);
        Log.d("배터리액티비티 ","onResume");
        }

        }//Activity end
