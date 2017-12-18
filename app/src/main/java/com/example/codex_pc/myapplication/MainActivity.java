package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPersistance.getDatabase();
        Button techerLogin = (Button)findViewById(R.id.teacherlogin);
        Button studentLogin = (Button)findViewById(R.id.studentbtn);
        techerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this,authorlogin.class);
                MainActivity.this.startActivity(login);
            }
        });
        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    Intent studentevent = new Intent(MainActivity.this,StudentActivity.class);
                    startActivity(studentevent);
                }
                else{
                    Intent studentevent = new Intent(MainActivity.this,StudentLogin.class);
                    startActivity(studentevent);
                }
            }
        });
    }
}
