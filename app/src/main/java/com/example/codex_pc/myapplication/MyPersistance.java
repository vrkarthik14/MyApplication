package com.example.codex_pc.myapplication;

import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CODEX_PC on 13-12-2017.
 */

public class MyPersistance extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
