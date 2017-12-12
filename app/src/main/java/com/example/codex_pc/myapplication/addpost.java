package com.example.codex_pc.myapplication;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CODEX_PC on 28-11-2017.
 */

public class addpost implements Serializable{

    private String type;
    private Date dateobj;
    private String details;
    private String subject;

    public addpost(){

    }

    public  addpost(String type,Date date,String details,String subject){
        this.type = type;
        this.dateobj = date;
        this.details = details;
        this.subject = subject;
    }

    public String getType(){
        //Log.i("getType",type);
        return this.type;}

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return dateobj;
    }

    public void setDate(Date date) {
        this.dateobj = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
