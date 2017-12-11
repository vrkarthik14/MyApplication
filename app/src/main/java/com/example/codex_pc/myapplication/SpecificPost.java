package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SpecificPost extends AppCompatActivity {

    Button notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_post);
        

        TextView title = (TextView)findViewById(R.id.TitleField);
        TextView dateField = (TextView)findViewById(R.id.DateField);
        TextView description = (TextView)findViewById(R.id.DescriptionField);

        Intent i = getIntent();
        final addpost maddpost = (addpost)i.getSerializableExtra("addpost");

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date1 = df.format(maddpost.getDate());

        Log.i("typetitle",maddpost.getType());
        title.setText(maddpost.getType());
        dateField.setText(date1);
        description.setText(maddpost.getDetails());
        notify = (Button)findViewById(R.id.notify);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = maddpost.getDate().getTime();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("title",maddpost.getType());
                intent.putExtra("beginTime",startTime);
                startActivity(intent);
            }
        });
    }
}
