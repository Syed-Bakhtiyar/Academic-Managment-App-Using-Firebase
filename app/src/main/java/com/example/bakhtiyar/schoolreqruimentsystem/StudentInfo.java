package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 2/19/2017.
 */
public class StudentInfo {

    String uid, push;
    String name, fname,qual,dateOfBirth,classs,address,email,phone,ismale;
    String url;
    int age;

    boolean selected;

    public StudentInfo(String uid, String push, String name, String fname, String qual, String dateOfBirth, String classs, String address, String email, String phone, String ismale,String url,int age) {
        this.uid = uid;
        this.push = push;
        this.name = name;
        this.fname = fname;
        this.qual = qual;
        this.dateOfBirth = dateOfBirth;
        this.classs = classs;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.ismale = ismale;
        this.url=url;

        this.age = age;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public int getAge() {
        return age;
    }

    public String getUrl() {
        return url;
    }

    public String getPush() {
        return push;
    }

    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public String getQual() {
        return qual;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getClasss() {
        return classs;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getIsmale() {
        return ismale;
    }

    public StudentInfo() {

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
