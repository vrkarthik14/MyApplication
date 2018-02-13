package com.example.codex_pc.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Wallet extends AppCompatActivity {

    DatabaseReference reference;
    int yearly_buget;
    int rent_allowancce;
    int food_allowance,other;
    ListView list;

    Button addExpense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        reference = FirebaseDatabase.getInstance().getReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("expenses");


        list = (ListView) findViewById(R.id.myExpense);
        addExpense = (Button) findViewById(R.id.add_expense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertadd = new AlertDialog.Builder(Wallet.this);
                LayoutInflater factory = LayoutInflater.from(Wallet.this);
                View view = factory.inflate(R.layout.add_expense,null);
                final EditText date = (EditText) view.findViewById(R.id.expense_date);
                final EditText place = (EditText) view.findViewById(R.id.place);
                final EditText amont = (EditText) findViewById(R.id.expesne_amount);
                final EditText type = (EditText) findViewById(R.id.expense_type);

                alertadd.setView(view);
                alertadd.setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(date.getText().toString().equals("")&&!amont.getText().toString().equals("")){
                            reference.push().setValue(new addExpense(place.getText().toString(),type.getText().toString(),amont.getText().toString(),date.getText().toString()));
                        }
                    }
                });
                alertadd.show();


            }
        });




    }
}
