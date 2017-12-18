package com.example.codex_pc.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CODEX_PC on 13-12-2017.
 */

public class addevent1 extends Fragment {
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
    private String type, detail, date;
    private Date d1;
    private Spinner mspinner;
    private Spinner mspinnerSection;
    private String subject;
    private String section;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_addevent, container, false);
        inpost = (Button) rootview.findViewById(R.id.post);
        intype = (EditText) rootview.findViewById(R.id.type);
        indate = (EditText) rootview.findViewById(R.id.date);
        indetails = (EditText) rootview.findViewById(R.id.detail);

        mspinner = (Spinner) rootview.findViewById(R.id.spinner);
        mspinnerSection = (Spinner) rootview.findViewById(R.id.SectionSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.subjects, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mspinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> Sectionadapter = ArrayAdapter.createFromResource(this.getContext(),R.array.sections,android.R.layout.simple_spinner_item);
        Sectionadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mspinnerSection.setAdapter(Sectionadapter);

        mfirebaseStorage = FirebaseStorage.getInstance();



        inpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post(v);
            }
        });





        return rootview;
    }

    protected void post(View view) {
        type = intype.getText().toString();
        detail = indetails.getText().toString();
        date = indate.getText().toString();
        subject = mspinner.getSelectedItem().toString();
        section = mspinnerSection.getSelectedItem().toString();

        if(!section.equals("Select Your Section")){
            mdatabaseReference = FirebaseDatabase.getInstance().getReference().child(section);

            try {
                d1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);



            if (!type.equals("")&& d1 != null && !detail.equals("")) {
                addpost newpost = new addpost(type, d1, detail, subject);
                mdatabaseReference.push().setValue(newpost);

                indate.setText("");
                intype.setText("");
                indetails.setText("");
                Toast.makeText(this.getContext(), "Your message is succesfully Posted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.getContext(), "You can not leave any field blank!!", Toast.LENGTH_SHORT).show();
            }
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(this.getContext(), "Please enter valid Date  ", Toast.LENGTH_SHORT).show();
            }



        }else{
            Toast.makeText(this.getContext(), "Please select section to which you want post this message.", Toast.LENGTH_SHORT).show();
        }




    }




}
