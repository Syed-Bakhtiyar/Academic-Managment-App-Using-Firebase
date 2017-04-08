package com.example.bakhtiyar.schoolreqruimentsystem;

/**
 * Created by Bakhtiyar on 2/14/2017.
 */
public class ApprovalClass {

    String muid;

    String tuid;

    String cname;

    public ApprovalClass() {
    }

    public String getMuid() {

        return muid;
    }

    public String getTuid() {
        return tuid;
    }

    public String getCname() {
        return cname;
    }

    public ApprovalClass(String muid, String tuid, String cname) {
        this.muid = muid;
        this.tuid = tuid;
        this.cname = cname;
    }
}
