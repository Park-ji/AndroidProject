package com.example.secondproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocatoinActivity extends AppCompatActivity {

    TextView result;
    Button mapBtn;

    double lat;
    double lon;
    double alt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locatoin);
        result = findViewById(R.id.result);
        mapBtn = findViewById(R.id.mapBtn);
        //0. 위치 파악 gps 또는 네트워크 자원 사용 권한 부여 : 매니페스트 파일

        //0-1. 매니페스트 설정 확인 : 버전에 따라 다름(사용할수도 안할수도 있음)
        //설명? 현재 스스로 매니페스트 permisson중에 ACCESS_COARSE_LOCATION 설정해놨는지 확인
        ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
        //0-2. 사용자 허락 요청 창
        //requestCode는 아무거나 정수값이면 됨
        //requestPermissions를 사용하면? 작은 alert창같은 창이 뜸  => 해당 창을 의미하기위해서 this 사용 => 의미? MyLocationActivity.this
        //이때, getApplicationContext사용하면? 현재 액티비티를 가르키기 때문에 X
        ActivityCompat.requestPermissions(MyLocatoinActivity.this, new String[]{ android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);


        //1. 안드로이드 시스템 gps / 네트워크 위치제공 서비스 설정
        LocationManager lm = (LocationManager) getSystemService((Context.LOCATION_SERVICE));

        //2. 위치정보 파악
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); //최근에 파악한 위치 정보 리턴
        //폰에서 테스트? LocationManager.GPS_PROVIDER -> 폰은 gps정보를 먼저 잡음
        //avd에서 테스트? LocationManager.NETWORK_PROVIDER

        //3. location 값 분석
        String provider = location.getProvider(); //위치파악하는데 사용한 provider 반환 : gps or network
        //위도, 경도, 고도
        lat = location.getLatitude();//위도
        lon = location.getLongitude();//경도
        alt = location.getAltitude();//고도
        //avd에서 기본값으로 찾아오는 위치값? 구글 본사

        //4. 결과 출력
        result.setText("내위치제공자 = "+provider+"\n내위치\n 위도 = " + lat + "\n 경도 = " + lon + "\n 고도 = " + alt );

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String location  = String.valueOf(lat) + ", " + String.valueOf(lon);
                String http ="http://maps.google.com/maps?q="+lat+", "+lon;
                Uri uri = Uri.parse(http);
                Intent i = new Intent(Intent.ACTION_VIEW,uri);//ACTION_VIEW : 해당 url을 보여줌(홈페이지나 맵이나 같은 동작)
                startActivity(i);
            }
        });

        //5. 이동할 때마다 위치 추적
        //Listener는 Interface
        //interface 모든 메소드 추상적 - 리스너 상속 무명클래스 메소드 구현
        //설령 아무런 구현안한다고 해도 다 선언 해줘야함
        //ex) LocationListner 상속 무명클래스 4개 -> 4개 다 쓰지 않아도 4개 다 선언
        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //매개변수 Loaction loction의 의미? location 변수 만들어져 있음 = 1,2번 과정 필요 X
                String provider = location.getProvider(); //위치파악하는데 사용한 provider 반환 : gps or network
                //위도, 경도, 고도
                lat = location.getLatitude();//위도
                lon = location.getLongitude();//경도
                alt = location.getAltitude();//고도
                Toast.makeText
                 (getApplicationContext(),
                "내위치제공자 = "+provider+"\n내위치\n 위도 = " + lat + "\n 경도 = " + lon + "\n 고도 = " + alt,
                 Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }
            @Override
            public void onProviderEnabled(String provider) {

            }
            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //매개변수(제공자, 최소시간, 최소거리, LocationListener inner class 객체
        //1000 = 1초 & 1 = 1m
        //의미? 1초마다 위치변화 체크하고 위치가 변화되었다는 기준이 1m이상 차이 여부
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, ll);

    }
}























