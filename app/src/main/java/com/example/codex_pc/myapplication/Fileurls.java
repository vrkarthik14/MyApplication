package com.example.codex_pc.myapplication;

/**
 * Created by CODEX_PC on 02-12-2017.
 */

public class Fileurls {
    private String url1;
    private String name;

    public Fileurls(){

    }

    public Fileurls(String url1,String name){
        this.url1 = url1;this.name = name;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
