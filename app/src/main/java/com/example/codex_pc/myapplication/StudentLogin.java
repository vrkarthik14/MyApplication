package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by CODEX_PC on 14-12-2017.
 */

public class StudentLogin extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressBar mprogressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPass = (EditText) findViewById(R.id.loginPass);
        // newly added code
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mprogressbar = (ProgressBar)findViewById(R.id.Login_progressBar);



    }
    public void loginButtonClicked(View view) {
        String email = loginEmail.getText().toString().trim();
        String pass = loginPass.getText().toString().trim();

        mprogressbar.setVisibility(View.VISIBLE);


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        checkUserExists();
                        mprogressbar.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(StudentLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        mprogressbar.setVisibility(View.GONE);
                    }
                }
            });
        }else{
            Toast.makeText(this, "No fields can be Empty ", Toast.LENGTH_SHORT).show();
            mprogressbar.setVisibility(View.GONE);
        }
    }
    public void signupButton(View view){
        startActivity(new Intent(StudentLogin.this,StudentRegistration.class));
        finish();
    }
    public void checkUserExists() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(user_id)) {
                    Intent loginIntent = new Intent(StudentLogin.this, StudentActivity.class);
                    startActivity(loginIntent);
                    Log.i("entred","ya");
                    finish();
                }else{
                    if(FirebaseAuth.getInstance().getCurrentUser() != null){
                        AuthUI.getInstance().signOut(StudentLogin.this);
                    }
                    Toast.makeText(StudentLogin.this, "Not registered please register.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(StudentLogin.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
