package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyPersistance.getDatabase();
        Button studentLogin = (Button)findViewById(R.id.studentbtn);

        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            startActivity(new Intent(MainActivity.this,StudentLogin.class));
        }

        Button wallet = (Button) findViewById(R.id.wallet);
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Wallet.class));
            }
        });


        studentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent studentevent = new Intent(MainActivity.this,StudentActivity.class);
                    startActivity(studentevent);

            }
        });
    }
}
