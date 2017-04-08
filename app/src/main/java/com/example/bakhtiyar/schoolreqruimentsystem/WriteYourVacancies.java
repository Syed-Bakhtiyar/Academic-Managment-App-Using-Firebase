package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 2/12/2017.
 */
public class WriteYourVacancies {
    String qual;

    String exp;

    String skills;

    String uid;

    String push;

    String name;

    public String getName() {
        return name;
    }

    public String getQual() {
        return qual;
    }

    public String getExp() {
        return exp;
    }

    public String getSkills() {
        return skills;
    }

    public String getUid() {
        return uid;
    }

    public String getPush() {
        return push;
    }

    public WriteYourVacancies() {

    }

    public WriteYourVacancies(String qual, String exp, String skills, String uid, String push, String name) {
        this.qual = qual;

        this.name = name;
        this.exp = exp;
        this.skills = skills;
        this.uid = uid;
        this.push = push;
    }
}
