package com.example.flipcard;

public class TimeCount {
    private static long count = 0;
    private static String record ="";

    public void setcount(long count){
        this.count = count;
    }

    public long getcount(){
        return count;
    }

    public void setRecord(String record) {this.record = record;}

    public String getRecord() {return record; }

}
