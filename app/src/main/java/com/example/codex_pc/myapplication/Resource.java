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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by CODEX_PC on 16-12-2017.
 */

public class Resource extends Fragment {


    private ImageButton imageButton;
    final static int PICK_PDF_CODE = 2342;
    private StorageReference storageReference;
    private UploadTask uploadTask;
    private DatabaseReference databaseReference;
    private Button upload;
    private Uri uri;
    private StorageReference fileref;
    private EditText des;
    private String descriptoin="";
    private TextView selectText;
    private ProgressBar progressBar;
    private TextView uploadNote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.resource,container,false);

        imageButton = (ImageButton)rootView.findViewById(R.id.FilePickerBtn);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("downloadurl");
        upload = (Button)rootView.findViewById(R.id.uploadBtn);
        des = (EditText)rootView.findViewById(R.id.DesriptionFieldPost);
        selectText = (TextView)rootView.findViewById(R.id.FilePickerText);
        progressBar = (ProgressBar)rootView.findViewById(R.id.ResourceProgress);
        uploadNote =(TextView)rootView.findViewById(R.id.uploadNote);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptoin = des.getText().toString();
                if (!descriptoin.equals("") && fileref != null&&uri!=null) {
                    progressBar.setVisibility(View.VISIBLE);
                    uploadNote.setText("Please wait till currently selected file is completely is uploaded ....");
                    uploadTask = fileref.putFile(uri);
                    try {
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Uri downloadUri = taskSnapshot.getDownloadUrl();
                                Fileurls resouce = new Fileurls(downloadUri.toString(), descriptoin);
                                databaseReference.push().setValue(resouce);
                                Log.i("suddesd", "yes");

                                Log.i("evrt", "yes");
                                Log.i("DOwns", taskSnapshot.getDownloadUrl().toString());
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "File uploaded successfully..", Toast.LENGTH_SHORT).show();
                                selectText.setText("Please select file/photo to upload .");
                                des.setText("");
                                uploadNote.setText("");
                                uri = null;

                            }
                        });
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Upload failed", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                Log.i("Excsd",e.toString());
                                uploadNote.setText("");
                            }
                        });
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Upload failed..", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "No field can be empty !! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_CODE) {
            if (data != null) {
                uri = data.getData();

                selectText.setText("Pdf is Selected ");
                fileref = storageReference.child(uri.getLastPathSegment());
            }
        }

    }
}
