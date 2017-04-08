package com.example.bakhtiyar.schoolreqruimentsystem.MainFragments;

/**
 * Created by Bakhtiyar on 2/15/2017.
 */
public class TeachersPostClass {

    String name, muid, tuid, push, url,post,filetype;


    public TeachersPostClass(String name, String muid, String tuid, String push, String url,String post,String filetype) {
        this.name = name;
        this.muid = muid;
        this.tuid = tuid;
        this.push = push;
        this.url = url;
        this.post=post;
    }

    public String getFiletype() {
        return filetype;
    }

    public String getName() {
        return name;
    }

    public String getMuid() {
        return muid;
    }

    public String getTuid() {
        return tuid;
    }

    public String getPost() {
        return post;
    }

    public String getPush() {

        return push;
    }

    public String getUrl() {
        return url;
    }

    public TeachersPostClass() {

    }
}
