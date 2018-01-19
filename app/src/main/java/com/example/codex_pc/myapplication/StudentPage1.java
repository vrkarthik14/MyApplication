package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

/**
 * Created by CODEX_PC on 09-12-2017.
 */

public class StudentPage1 extends Fragment{

    private RecyclerView recyclerView;
    private DatabaseReference mdatabaseReference;
    private DatabaseReference mref;
    private String Usersection;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.studentpage,container,false);
        mdatabaseReference  = FirebaseDatabase.getInstance().getReference();
        recyclerView = (RecyclerView)rootview.findViewById(R.id.listView);
        Log.i("EnteredOncreate","yes");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.i("Userid",user.getUid());

        mref = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).child("Section");
        String sectiona = mref.getKey();
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 Usersection = dataSnapshot.getValue(String.class);
                Log.i("UseriA",Usersection);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Log.i("UseriD",sectiona);

        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();

        Runnable r = new Runnable() {
            @Override
            public void run(){
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Usersection);

        Log.i("In Onstart","yes");
        final FirebaseRecyclerAdapter<addpost,PostviewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<addpost, PostviewHolder>(
                addpost.class,
                R.layout.list_card,
                PostviewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(PostviewHolder viewHolder, final addpost model, int position) {
                viewHolder.setTitle(model.getType());
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
                String edate = sdf.format(model.getDate());
                viewHolder.setdate(edate);
                viewHolder.setDetails(model.getDetails());
                viewHolder.setSubject(model.getSubject());
                Log.i("entered ","onStart");
                final addpost maddpost = new addpost(model.getType(),model.getDate(),model.getDetails(),model.getSubject());
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),SpecificPost.class);
                        intent.putExtra("addpost",maddpost);
                        startActivity(intent);
                        //Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                        Log.i("clicked","onCLick");
                    }
                });

            }
        };

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                firebaseRecyclerAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(firebaseRecyclerAdapter);

            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 1000); // <-- the "1000" is the delay time in miliseconds.

    }


    public static class PostviewHolder extends RecyclerView.ViewHolder{

        View mview;
        public PostviewHolder(View itemView) {
            super(itemView);
            mview = itemView;
        }
        public void setTitle(String title){
            TextView posttitle = (TextView) mview.findViewById(R.id.postView);
            posttitle.setText(title);
        }
        public void setdate(String date){
            TextView posttitle = (TextView) mview.findViewById(R.id.dateView);
            posttitle.setText("Date: "+date);
        }
        public void setDetails(String details){
            TextView detailView = (TextView)mview.findViewById(R.id.detailView);
            detailView.setText(details);
        }
        public void setSubject(String subject){
            TextView subjectView = (TextView)mview.findViewById(R.id.subject);
            subjectView.setText(subject);
        }



    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
