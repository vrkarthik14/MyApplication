package com.example.codex_pc.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentNotes extends Fragment {

    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseReference;
    private FirebaseAuth mfirebaseAuth;
    private ChildEventListener mChildEventListener;
    private FileAdapter mFileAdapter;
    private ListView mlistView;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_notes);
//
//        mfirebaseDatabase = FirebaseDatabase.getInstance();
//        mdatabaseReference = FirebaseDatabase.getInstance().getReference().child("downloadurl");
//
//        mlistView = (ListView)findViewById(R.id.listview1);
//        final List<Fileurls> urls = new ArrayList<>();
//        mFileAdapter= new FileAdapter(this,R.layout.item,urls);
//        mlistView.setAdapter(mFileAdapter);
//
//        attachDatabsereadlistener();
//        Log.i("Entered","onCreate");
//
//
//
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_student_notes, container, false);
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference().child("downloadurl");
        mlistView = (ListView)rootView.findViewById(R.id.listview1);
        final List<Fileurls> urls = new ArrayList<>();
        mFileAdapter= new FileAdapter(this.getContext(),R.layout.file1,urls);
        mlistView.setAdapter(mFileAdapter);
        attachDatabsereadlistener();


        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        detachDatabaseReadListener();
    }


    private void attachDatabsereadlistener (){

        // if(mChildEventListener == null){
             Log.i("readListener","was null");
        mChildEventListener = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Fileurls fileurls = dataSnapshot.getValue(Fileurls.class);
                mFileAdapter.add(fileurls);
                Log.i("data","added");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("data","added");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("data","added");

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("data","added");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                Log.i("error","database error");
//                Log.i("error",databaseError.toString());
//                addpost maddpost = new addpost("exam",null,"no more",);
//                mFileAdapter.add(maddpost);




            }
        };
        mdatabaseReference.addChildEventListener(mChildEventListener);
        Log.i("Child","null");
    }

    private void detachDatabaseReadListener() {

        if (mChildEventListener != null) {
            mdatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }
}
