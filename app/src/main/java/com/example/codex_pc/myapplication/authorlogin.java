package com.example.codex_pc.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class authorlogin extends AppCompatActivity {

    private EditText email,password;
    private FirebaseAuth auth;
    private Button signUpbtn,signInbtn,btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorlogin);

        Firebase.setAndroidContext(this);

        auth = FirebaseAuth.getInstance();

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        signInbtn = (Button)findViewById(R.id.signinbtn);
        signUpbtn = (Button)findViewById(R.id.signupbtn);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(authorlogin.this);
                builder.setTitle("Get a odfficial acount");
                builder.setMessage("Are you a lecture of RIT ISE section c.Please register your mail-id to get an account");

                builder.setPositiveButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL,new String[]{"vrkarthik14@gmail.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT,"Get a posting account");
                        i.putExtra(Intent.EXTRA_TEXT,"Please provide your official email id and delete this content before sending email.");

                        startActivity(Intent.createChooser(i,"Send mail.."));

                        dialog.dismiss();

                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }




        });

        signInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputemail = email.getText().toString();
                final String inputpassword = password.getText().toString();

                if(TextUtils.isEmpty(inputemail)){
                    Toast.makeText(authorlogin.this, "Enter valid email adrees", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(inputpassword)){
                    Toast.makeText(authorlogin.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(inputemail,inputpassword)
                        .addOnCompleteListener(authorlogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                     if(!task.isSuccessful()){
                                         if(inputpassword.length() < 6) {
                                             password.setError("password lenght less than 6");
                                         }else {
                                             Toast.makeText(authorlogin.this, "Email or password incorrect", Toast.LENGTH_SHORT).show();
                                         }
                                     }else{
                                         //Log.i("user:",auth.getCurrentUser().toString());
                                         Intent intent = new Intent(authorlogin.this,LectureActivity.class);
                                         startActivity(intent);
//                                         finish();

                                     }
                            }
                        });

            }
        });

    }
}
