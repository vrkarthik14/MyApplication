package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addNewStudentPost extends AppCompatActivity {
    EditText USN,projectTitle,userDetails;
    public FirebaseDatabase fire;
    public DatabaseReference fireref;


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student_post);
        fire=FirebaseDatabase.getInstance();
        fireref=fire.getReference().child("Posts");



    }


    public void postButton(View view){

        USN = (EditText)findViewById(R.id.USN);
        projectTitle = (EditText)findViewById(R.id.projectTitle);
        // projectDescription = (EditText)findViewById(R.id.projectDescription);
        userDetails = (EditText)findViewById(R.id.Name);

        final String usn = USN.getText().toString().trim();
        final String pTitle = projectTitle.getText().toString().trim();
        //final String pDesc = projectDescription.getText().toString().trim();
        final String userdetail = userDetails.getText().toString().trim();


        if(!TextUtils.isEmpty(usn)&&!TextUtils.isEmpty(pTitle)&&!TextUtils.isEmpty(userdetail)){

            Intent intent =new Intent(addNewStudentPost.this,StudentActivity.class);
            final DatabaseReference newPost = fireref.push();
            fireref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newPost.child("projectTitle").setValue(pTitle);
                    newPost.child("userDetails").setValue(userdetail);
                    newPost.child("usn").setValue(usn);
                    //newPost.child("projectDesc").setValue(pDesc);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            Log.i("this worked fine","no error here");

            startActivity(intent);
        }
        else{
            Toast.makeText(addNewStudentPost.this,"Enter all fields",Toast.LENGTH_LONG).show();


        }



    }
}
