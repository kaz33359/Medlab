package com.example.hospital_mgmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class doctor_Profile extends AppCompatActivity implements View.OnClickListener {

    private Button doctorupdate_Btn;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatabaseReference databaseReference;
    private EditText doctorName_Et, doctorAge_Et,doctorHospital_Et, doctorPhoneNo_Et, doctorAdress_Et, doctorQualification_Et;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        doctorName_Et = (EditText) findViewById(R.id.doctorname_et);
        doctorAge_Et = (EditText) findViewById(R.id.doctorage_et);
        doctorHospital_Et = (EditText) findViewById(R.id.doctorhospital_et);
        doctorPhoneNo_Et = (EditText) findViewById(R.id.doctorphoneno_et);
        doctorAdress_Et = (EditText) findViewById(R.id.doctoraddress_et);
        doctorQualification_Et = (EditText) findViewById(R.id.doctorqualification_et);
        doctorupdate_Btn = (Button) findViewById(R.id.doctorupdate_btn);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_btn);

        doctorupdate_Btn.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
       // FirebaseUser user = firebaseAuth.getCurrentUser();

    }


    private void saveUserInformation(){

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        String name = doctorName_Et.getText().toString().trim();
        String age = doctorAge_Et.getText().toString().trim();
        //String specialisation = doctorSpecialisation_Et.getText().toString().trim();
        String phoneno = doctorPhoneNo_Et.getText().toString().trim();
        String address = doctorAdress_Et.getText().toString().trim();
        String gender = radioButton.getText().toString().trim();
        String qualification = doctorQualification_Et.getText().toString().trim();
        String hospital = doctorHospital_Et.getText().toString().trim();
        String usertype = "doctor";

        Doctorinformation doctorinformation = new Doctorinformation(name, age, phoneno, address, gender, qualification, hospital, usertype);

        user = firebaseAuth.getCurrentUser();

       // final String docemail = user.getEmail();

        databaseReference.child("doctors").child(user.getDisplayName()).setValue(doctorinformation);


        Toast.makeText(this, "Informtion Saved...",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {
        if (v == doctorupdate_Btn) {
            saveUserInformation();
            finish();
            startActivity(new Intent(getApplicationContext(), doctor_Dashboard.class));
        }

    }
}

