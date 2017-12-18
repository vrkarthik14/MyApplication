package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CODEX_PC on 11-12-2017.
 */

public class StudentPost1 extends android.support.v4.app.Fragment {

    DatabaseReference myRefernceMarker;
    private RecyclerView mPostsList;
    private FirebaseAuth mfirebaseAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        final View rootview = inflater.inflate(R.layout.activity_student_post,container,false);

        mPostsList = (RecyclerView) rootview.findViewById(R.id.my_recycler_view_posts);
        mPostsList.setHasFixedSize(true);
        mPostsList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.addbig);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),addNewStudentPost.class);
                startActivity(intent);
                getActivity().finish();;

            }
        });


        myRefernceMarker = FirebaseDatabase.getInstance().getReference().child("Posts");

        return rootview;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<postData,postsViewHolder> FBRA = new FirebaseRecyclerAdapter<postData, postsViewHolder>(
                postData.class,
                R.layout.postsrow,
                postsViewHolder.class,
                myRefernceMarker
        ) {
            @Override
            protected void populateViewHolder(postsViewHolder viewHolder, postData model, int position) {
                viewHolder.setTitle(model.getProjectTitle());
                viewHolder.setDesc(model.getUsn());
                viewHolder.setUserdetails(model.getUserDetails());
            }
        };

        mPostsList.setAdapter(FBRA);
    }

    public static class postsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public postsViewHolder(View itemview) {
            super(itemview);
            mView = itemview;
        }

        public void setTitle(String title) {
            TextView postTitle = (TextView) itemView.findViewById(R.id.Title);
            postTitle.setText(title);
        }

        public void setDesc(String desc) {
            TextView postDesc = (TextView) itemView.findViewById(R.id.Description);
            postDesc.setText(desc);
        }

        public void setUserdetails(String userdetails) {
            TextView postuserdetails = (TextView) itemView.findViewById(R.id.userDetails);
            postuserdetails.setText("Posted By: "+userdetails);
        }
    }




}
