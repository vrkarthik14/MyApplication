package com.example.codex_pc.myapplication;

/**
 * Created by CODEX_PC on 11-12-2017.
 */

public class postData {
    public String usn,userDetails,projectTitle;

    public postData(){}


    public postData(String usn, String userDetails, String projectTitle) {
        this.usn = usn;
        this.userDetails = userDetails;
        this.projectTitle = projectTitle;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }
}
