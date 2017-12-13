package com.example.codex_pc.myapplication;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CODEX_PC on 13-12-2017.
 */

public class MyPersistance {

    private static FirebaseDatabase firebaseDatabase;
    public static FirebaseDatabase getDatabase(){
        if(firebaseDatabase == null){
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        return firebaseDatabase;
    }

}
