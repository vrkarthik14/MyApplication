package com.example.codex_pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by CODEX_PC on 14-12-2017.
 */

public class StudentRegistration extends AppCompatActivity {

    private EditText name,email,password,yearlyBudget,monthlyRent,foodAllowance,otherExpenses;
    private FirebaseAuth mAuth;
    private String section;
    private DatabaseReference mDatabase;
    private Spinner mspinner;
    private ProgressBar mprogressBar;
    private Button signupButton;
    private Button LognIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        name = (EditText) findViewById(R.id.Username);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
        yearlyBudget=(EditText) findViewById(R.id.YearlyBudget);
        monthlyRent=(EditText) findViewById(R.id.MonthlyRent);
        foodAllowance=(EditText) findViewById(R.id.FoodAllowance);
        otherExpenses=(EditText) findViewById(R.id.Other);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mprogressBar= (ProgressBar)findViewById(R.id.RegisterProgressBar);
         mspinner = (Spinner) findViewById(R.id.sections);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sections, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mspinner.setAdapter(adapter);
        signupButton = (Button)findViewById(R.id.signupButton) ;
        LognIn = (Button)findViewById(R.id.StudentLogInBtn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_content,password_content,email_content,section,budget_content,rent_content,foodal_content,other_content;
                name_content= name.getText().toString().trim();
                password_content = password.getText().toString().trim();
                email_content=  email.getText().toString().trim();
                budget_content=yearlyBudget.getText().toString().trim();
                rent_content=monthlyRent.getText().toString().trim();
                foodal_content=foodAllowance.getText().toString().trim();
                other_content=otherExpenses.getText().toString().trim();
                final int total = Integer.parseInt(budget_content);
                final int rent = Integer.parseInt(rent_content);
                final int food = Integer.parseInt(foodal_content);
                final int other = Integer.parseInt(other_content);
                final int sum=rent+food+other;
                section= mspinner.getSelectedItem().toString();
                mprogressBar.setVisibility(View.VISIBLE);



                if (!TextUtils.isEmpty(email_content) && !TextUtils.isEmpty(name_content) && !TextUtils.isEmpty(password_content)&& !TextUtils.isEmpty(budget_content) && !TextUtils.isEmpty(rent_content) && !TextUtils.isEmpty(foodal_content) && !TextUtils.isEmpty(other_content) && !section.equals("Select Your Section")){
                    mAuth.createUserWithEmailAndPassword(email_content,password_content).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                    if(total>sum){
                                        String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = mDatabase.child(user_id);
                                current_user_db.child("Username_Name").setValue(name_content);
                                current_user_db.child("Yearly Budget").setValue(budget_content);
                                current_user_db.child("Monthly Rent").setValue(rent_content);
                                current_user_db.child("Food Allowance").setValue(foodal_content);
                                current_user_db.child("Other Expenses").setValue(other_content);
                                current_user_db.child("Section").setValue(section);
                                mprogressBar.setVisibility(View.GONE);
                                startActivity(new Intent(StudentRegistration.this,StudentActivity.class));
                                finish();
                                Toast.makeText(StudentRegistration.this, "Successfully Registered ", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(StudentRegistration.this, "Review monthly budget", Toast.LENGTH_LONG).show();
                                    }

                            }
                            else {
                                Toast.makeText(StudentRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                mprogressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

                    else if(section.equals("Select Your Section")){
                    Toast.makeText(getApplicationContext(), "Select your section ", Toast.LENGTH_SHORT).show();
                    mprogressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "No field must be empty!!", Toast.LENGTH_SHORT).show();
                    mprogressBar.setVisibility(View.GONE);
                }
            }
        });

        LognIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonClicked();
            }
        });
    }

    public void loginButtonClicked(){
        startActivity(new Intent(StudentRegistration.this,StudentLogin.class));
        finish();
    }

}
