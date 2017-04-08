package com.example.bakhtiyar.schoolreqruimentsystem.MainFragments;

/**
 * Created by Bakhtiyar on 2/20/2017.
 */
public class AdmissionPostingClass {

    String name, primary,secondary,ssc,hsc;

    String science, computer, pre_engingeering,pre_Medical,commerce,arts, uid, push;

    String to , from;

    public AdmissionPostingClass(String name,String primary, String secondary, String SSC, String HSC, String science, String computer, String pre_engingeering, String pre_Medical, String commerce, String arts, String uid, String push,String to, String from) {
        this.primary = primary;
        this.secondary = secondary;
        this.ssc = SSC;
        this.hsc = HSC;
        this.science = science;
        this.computer = computer;
        this.pre_engingeering = pre_engingeering;
        this.pre_Medical = pre_Medical;
        this.commerce = commerce;
        this.arts = arts;
        this.uid = uid;
        this.push = push;
        this.name = name;
        this.to = to;
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public void setPush(String push) {
        this.push = push;
    }

    public String getName() {
        return name;
    }

    public String getPrimary() {
        return primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public String getSsc() {
        return ssc;
    }

    public String getHsc() {
        return hsc;
    }

    public String getScience() {
        return science;
    }

    public String getComputer() {
        return computer;
    }

    public String getPre_engingeering() {
        return pre_engingeering;
    }

    public String getPre_Medical() {
        return pre_Medical;
    }

    public String getCommerce() {
        return commerce;
    }

    public String getArts() {
        return arts;
    }

    public String getUid() {
        return uid;
    }

    public String getPush() {
        return push;
    }

    public AdmissionPostingClass() {

    }
}
