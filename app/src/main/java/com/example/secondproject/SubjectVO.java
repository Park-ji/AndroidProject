package com.example.secondproject;

public class SubjectVO {
    //src : R.drawable.google은 안드로이드 시스템 내부에서 정수로 관리함 => 즉, src의 타입은 int
    int src;
    String name;
    String detail;

    public SubjectVO(){}

    public SubjectVO(int src, String name, String detail) {
        this.src = src;
        this.name = name;
        this.detail = detail;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
