package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addevent extends AppCompatActivity {

    private String uid = "";
    private Button inpost;
    private EditText intype;
    private EditText indate;
    private EditText indetails;
    private DatabaseReference mdatabaseReference;
    private FirebaseStorage mfirebaseStorage;
    private StorageReference mstoragerefernce;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String type,detail,date;
    private Date d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        //String d = new SimpleDateFormat("DD-MM-YYYY").format(new Date());

        inpost = (Button)findViewById(R.id.post);
        intype = (EditText)findViewById(R.id.type);
        indate = (EditText)findViewById(R.id.date);
        indetails = (EditText)findViewById(R.id.detail);

        mfirebaseStorage = FirebaseStorage.getInstance();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference().child("event");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user == null){
                    startActivity(new Intent(addevent.this,authorlogin.class));
                    finish();
                }
            }
        };

        uid = user.getUid();

        inpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post(v);
            }
        });







    }

    protected void post(View view){
        type = intype.getText().toString();
        detail = indetails.getText().toString();
        date = indate.getText().toString();

        try {
            d1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.i("exception ","here");
        }


        if(type!=null&&d1 != null && detail != null) {
            addpost newpost = new addpost(type, d1, detail);
            mdatabaseReference.push().setValue(newpost);

            indate.setText("");
            intype.setText("");
            indetails.setText("");
            Toast.makeText(this, "Your message is succesfully Posted.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "You can not leave any field blank!!", Toast.LENGTH_SHORT).show();
        }





    }



}
