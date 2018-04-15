package com.example.codex_pc.myapplication;

/**
 * Created by CODEX_PC on 13-02-2018.
 */

public class addExpense
{

    public String place,type,amount,date;

    public addExpense(String place, String type, String amount, String date) {
        this.place = place;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
