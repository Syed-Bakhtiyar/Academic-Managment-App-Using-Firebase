package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 3/4/2017.
 */
public class CommentsClass {

    String push, name,senderurl, commint, url;

    public CommentsClass() {
    }

    public String getPush() {

        return push;
    }

    public String getName() {
        return name;
    }

    public String getSenderurl() {
        return senderurl;
    }

    public String getCommint() {
        return commint;
    }

    public String getUrl() {
        return url;
    }

    public CommentsClass(String push, String name, String senderurl, String commint, String url) {
        this.push = push;
        this.name = name;
        this.senderurl = senderurl;
        this.commint = commint;
        this.url = url;
    }
}
