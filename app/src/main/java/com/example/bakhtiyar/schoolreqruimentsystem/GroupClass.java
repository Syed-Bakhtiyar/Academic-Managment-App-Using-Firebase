package com.example.bakhtiyar.schoolreqruimentsystem;

import java.util.ArrayList;

/**
 * Created by Bakhtiyar on 3/3/2017.
 */
public class GroupClass {

    String link;

    ArrayList<StudentInfo> arrayList;

    String groupname, manuid, push,tuid;

    public GroupClass() {
    }

    public ArrayList<StudentInfo> getArrayList() {

        return arrayList;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getManuid() {
        return manuid;
    }

    public String getPush() {
        return push;
    }

    public String getLink() {
        return link;
    }

    public String getTuid() {
        return tuid;
    }

    public GroupClass(String link, ArrayList<StudentInfo> arrayList, String groupname, String manuid, String push, String tuid) {

        this.link = link;
        this.arrayList = arrayList;
        this.groupname = groupname;
        this.manuid = manuid;
        this.push = push;
        this.tuid = tuid;
    }
}
