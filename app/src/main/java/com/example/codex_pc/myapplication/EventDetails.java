package com.example.codex_pc.myapplication;

/**
 * Created by CODEX_PC on 13-02-2018.
 */

public class EventDetails {
    private String name,date,venue,details,team_size,prize_money,reg_fee,contact;

    public EventDetails(String name, String date, String venue, String details, String team_size, String prize_money, String reg_fee, String contact) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.details = details;
        this.team_size = team_size;
        this.prize_money = prize_money;
        this.reg_fee = reg_fee;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTeam_size() {
        return team_size;
    }

    public void setTeam_size(String team_size) {
        this.team_size = team_size;
    }

    public String getPrize_money() {
        return prize_money;
    }

    public void setPrize_money(String prize_money) {
        this.prize_money = prize_money;
    }

    public String getReg_fee() {
        return reg_fee;
    }

    public void setReg_fee(String reg_fee) {
        this.reg_fee = reg_fee;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
