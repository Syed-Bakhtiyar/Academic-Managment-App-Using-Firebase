package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 3/6/2017.
 */
public class LikesClass {

    String name, uid, comid;

    public LikesClass(String name, String uid, String comid) {
        this.name = name;
        this.uid = uid;
        this.comid = comid;
    }

    public LikesClass() {
    }

    public String getName() {

        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getComid() {
        return comid;
    }
}
