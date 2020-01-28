package com.example.secondproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends BaseAdapter {
    //기본적으로 Adapter를 상속받으면 4가지 메소드 오버라이딩이 필수
    Context context;
    ArrayList<SubjectVO> list ;

    //Adapter의 기본적인 매개변수 : Context, 레이아웃, 데이터
    public SubjectAdapter(Context context, ArrayList<SubjectVO> list){ //컨텍스트, 보일 데이터
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() { //구현해야할 전체 아이템갯수
        return list.size();
    }

    @Override
    public Object getItem(int position) {//리스트뷰 내부에서 선택한 아이템의 값
        return list.get(position);//사용자가 선택한 값
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    //리스트뷰의 1개의 아이템? 뷰
    //즉, 리스트뷰에 포함되는 하나의 아이템(뷰)을 리턴
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView : 리스트뷰에 들어가는 1개의 아이템
        //parent : 아이템들로 구성된 전체 리스트뷰
        if(convertView==null){
            //아이템을 나타내는 뷰가 하나도 없는 상태 ex) 최초의 상태
            //자바 액티비티에서 xml에 정의된것을 부풀려서 사용시? inflator 사용
            convertView = LayoutInflater .from(context).inflate(R.layout.subject_item, null); //subject_item : 하나의 아이템을 구성하기 위해서 만든 xml
            //해당 레이아웃 없으면 null 들어감
            //즉, 1개의 아이템은 subject_item에서 가져와 구성하기
        }

        SubjectVO vo = (SubjectVO)getItem(position); //리스트에서 선택된 값
        ImageView image = convertView.findViewById(R.id.image); //image
        image.setImageResource(vo.getSrc());

        TextView name = convertView.findViewById(R.id.name);
        name.setText(vo.getName());

        TextView detail = convertView.findViewById(R.id.detail);
       detail.setText(vo.getDetail());

        return convertView;
    }
}












