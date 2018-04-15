package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private ImageButton imageButton;
    final static int PICK_PDF_CODE = 2342;
    private StorageReference storageReference;
    private UploadTask uploadTask;
    private Uri uri;
    private StorageReference fileref;
    private EditText des;
    private Button upload;
    private String descriptoin="";
    private TextView selectText;
    private ProgressBar progressBar;
    private TextView uploadNote;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_addevent, container, false);
        inpost = (Button) rootview.findViewById(R.id.post);
        intype = (EditText) rootview.findViewById(R.id.type);
        indate = (EditText) rootview.findViewById(R.id.date);
        indetails = (EditText) rootview.findViewById(R.id.detail);


        imageButton = (ImageButton)rootview.findViewById(R.id.FilePickerBtn);
        storageReference = FirebaseStorage.getInstance().getReference();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("downloadurl");
        upload = (Button)rootview.findViewById(R.id.uploadBtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
            }
        });

//        mspinner = (Spinner) rootview.findViewById(R.id.spinner);
//        mspinnerSection = (Spinner) rootview.findViewById(R.id.SectionSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
//                R.array.subjects, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        mspinner.setAdapter(adapter);
//
//        ArrayAdapter<CharSequence> Sectionadapter = ArrayAdapter.createFromResource(this.getContext(),R.array.sections,android.R.layout.simple_spinner_item);
//        Sectionadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        mspinnerSection.setAdapter(Sectionadapter);

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
//        subject = mspinner.getSelectedItem().toString();
  //      section = mspinnerSection.getSelectedItem().toString();


            mdatabaseReference = FirebaseDatabase.getInstance().getReference().child("A");

            try {

                d1 = new SimpleDateFormat("dd-MM-yyyy").parse(date);


                uploadTask = fileref.putFile(uri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        if (!type.equals("") && d1 != null && !detail.equals("")) {
                            addpost newpost = new addpost(type, d1, detail, "",downloadUri.toString());
                            mdatabaseReference.push().setValue(newpost);

                            indate.setText("");
                            intype.setText("");
                            indetails.setText("");
                            Toast.makeText(getContext(), "Your message is succesfully Posted.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "You can not leave any field blank!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception e){
                Log.i("eororr",e.toString());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }






    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_CODE) {
            if (data != null) {
                uri = data.getData();

                //selectText.setText("Pdf is Selected ");
                fileref = storageReference.child(uri.getLastPathSegment());
            }
        }

    }




}
