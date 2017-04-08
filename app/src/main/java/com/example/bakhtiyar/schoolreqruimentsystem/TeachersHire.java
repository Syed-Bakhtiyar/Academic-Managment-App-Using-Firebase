package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 1/23/2017.
 */
public class TeachersHire {
    Boolean isMale,flag;

    String name, qualification, experience, skills, phone, email, imglink;

    String pushKey;

    String uid;

    int age;

    double salary;

    public TeachersHire(Boolean isMale, String name, String qualification, String experience, String skills, String phone, String email, String imageLink, String pushKey, int age, double salary, String uid) {
        this.isMale = isMale;
        this.name = name;
        this.qualification = qualification;
        this.experience = experience;
        this.skills = skills;
        this.phone = phone;
        this.email = email;
        imglink = imageLink;
        this.pushKey = pushKey;
        this.age = age;
        this.salary = salary;
        this.uid=uid;
    }

    public TeachersHire() {
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {

        return flag;
    }

    public String getUid() {
        return uid;
    }

    public Boolean getMale() {
        return isMale;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getExperience() {
        return experience;
    }

    public String getSkills() {
        return skills;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImglink() {
        return imglink;
    }

    public String getPushKey() {
        return pushKey;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}
