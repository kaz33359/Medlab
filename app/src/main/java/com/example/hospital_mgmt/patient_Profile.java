package com.example.hospital_mgmt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class patient_Profile extends AppCompatActivity implements View.OnClickListener {

    private Button patientUpdate_Btn;
    private RadioGroup radioGroup, radioGroup_2;
    private RadioButton radioButton, radioButton_2;
    private DatabaseReference databaseReference;
    private EditText patientName_Et, patientAge_Et, patientAddress_Et, patientPhoneno_Et, patientEmergencyPh_Et, patientRelation_Et;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        patientName_Et = (EditText) findViewById(R.id.patientname_et);
        patientAge_Et = (EditText) findViewById(R.id.patientage_et);
        patientAddress_Et = (EditText) findViewById(R.id.patientaddress_et);
        patientPhoneno_Et = (EditText) findViewById(R.id.patientphoneno_et);
        patientEmergencyPh_Et = (EditText) findViewById(R.id.patientemergencyContactNo_et);
        patientRelation_Et = (EditText) findViewById(R.id.patientemergencyRelation_et);

        radioGroup = (RadioGroup) findViewById(R.id.patientradiogroup);
        radioGroup_2 = (RadioGroup) findViewById(R.id.patientradiogroup_2);

        patientUpdate_Btn = (Button) findViewById(R.id.patientupdate_btn);
        patientUpdate_Btn.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void saveUserInformation(){

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        int radioId_2 = radioGroup_2.getCheckedRadioButtonId();
        radioButton_2 = findViewById(radioId_2);

        String name = patientName_Et.getText().toString().trim();
        String age = patientAge_Et.getText().toString().trim();
        String address = patientAddress_Et.getText().toString().trim();
        String phoneno = patientPhoneno_Et.getText().toString().trim();
        String emergencyph = patientEmergencyPh_Et.getText().toString().trim();
        String relation = patientRelation_Et.getText().toString().trim();
        String gender = radioButton.getText().toString().trim();
        String bloodgroup = radioButton_2.getText().toString().trim();
        String usertype = "user";

        Userinformation userinformation = new Userinformation(name, age, address, phoneno, emergencyph, relation, gender, bloodgroup, usertype);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //databaseReference.child(user.getUid()).setValue(userinformation);

        databaseReference.child("users").child(user.getDisplayName()).setValue(userinformation);


        Toast.makeText(this, "Informtion Saved...",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {
        if (v == patientUpdate_Btn) {
            saveUserInformation();
            finish();
            startActivity(new Intent(getApplicationContext(), patient_Dashboard.class));
        }

    }
}
