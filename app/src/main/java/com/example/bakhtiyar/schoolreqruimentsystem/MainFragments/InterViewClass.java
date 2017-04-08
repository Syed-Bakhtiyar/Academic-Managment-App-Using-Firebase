package com.example.bakhtiyar.schoolreqruimentsystem.MainFragments;

/**
 * Created by Bakhtiyar on 2/13/2017.
 */
public class InterViewClass {

    String frkey, uid,name,push;

    String discription, date, time;

    public InterViewClass() {
    }

    public InterViewClass(String frkey, String uid, String name, String discription, String date, String time,String push) {

        this.frkey = frkey;
        this.uid = uid;
        this.name = name;
        this.discription = discription;
        this.date = date;
        this.time = time;
        this.push=push;

    }

    public String getPush() {
        return push;
    }

    public String getFrkey() {
        return frkey;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
