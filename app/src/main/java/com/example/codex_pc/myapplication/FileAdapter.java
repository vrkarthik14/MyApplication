package com.example.codex_pc.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by CODEX_PC on 02-12-2017.
 */

public class FileAdapter extends ArrayAdapter{

    private StorageReference mstorageReference;
    DownloadManager downloadManager;
    public FileAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Fileurls> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null) {
            Log.i("Convertview", "was null");

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.file1, parent, false);
        }

        TextView mtextView = (TextView)convertView.findViewById(R.id.resourceTextView);
        Button downloadbtn = (Button)convertView.findViewById(R.id.downloadbtn);
        final Fileurls file = (Fileurls) getItem(position);
        String addText = file.getName();
        mtextView.setText(addText);

        downloadbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("enteredOnclick","yes");
                Log.i("adress",file.getUrl1().toString());
                downloadManager = (DownloadManager)getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(file.getUrl1()));
                Log.i("Downaa",Uri.parse(file.getUrl1()).toString());
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

            }
        });
        return convertView;
    }



}

