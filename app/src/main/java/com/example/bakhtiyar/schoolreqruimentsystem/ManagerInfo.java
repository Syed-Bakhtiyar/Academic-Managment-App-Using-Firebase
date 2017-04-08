package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 2/12/2017.
 */
public class ManagerInfo {

    String campusname;

    String managername;

    String address;

    String phone;

    String email;

    String uid;

    public ManagerInfo(String campusname, String managername, String address, String phone, String email, String uid) {
        this.campusname = campusname;
        this.managername = managername;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.uid = uid;
    }

    public String getCampusname() {
        return campusname;
    }

    public String getManagername() {
        return managername;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public ManagerInfo() {

    }
}
