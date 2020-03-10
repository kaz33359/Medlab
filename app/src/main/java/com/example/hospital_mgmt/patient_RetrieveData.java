package com.example.hospital_mgmt;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class patient_RetrieveData extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView patientRetName_Tv, patientRetEmail_Tv, patientRetAge_Tv, patientRetAddress_Tv, patientRetPhoneno_Tv, patientRetEmergencyPh_Tv, patientRetRelation_Tv, patientRetGender_Tv, patientRetBloodGroup_Tv;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__retrieve_data);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        final String useremail = user.getEmail();

        patientRetName_Tv = (TextView) findViewById(R.id.patientretname_tv);
        patientRetEmail_Tv = (TextView) findViewById(R.id.patientretemali_tv);
        patientRetAge_Tv = (TextView) findViewById(R.id.patientretage_tv);
        patientRetAddress_Tv = (TextView) findViewById(R.id.patientretaddress_tv);
        patientRetPhoneno_Tv = (TextView) findViewById(R.id.patientretphoneno_tv);
        patientRetEmergencyPh_Tv = (TextView) findViewById(R.id.patientretemergencyph_tv);
        patientRetRelation_Tv = (TextView) findViewById(R.id.patientretemergencyRelation_tv);
        patientRetGender_Tv = (TextView) findViewById(R.id.patientretgender_tv);
        patientRetBloodGroup_Tv = (TextView) findViewById(R.id.patientretbloodgroup_tv);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getDisplayName());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
                String phoneno = dataSnapshot.child("phoneno").getValue().toString();
                String emergencyph = dataSnapshot.child("emergencyph").getValue().toString();
                String relation = dataSnapshot.child("relation").getValue().toString();
                String gender = dataSnapshot.child("gender").getValue().toString();
                String bloodgroup = dataSnapshot.child("bloodgroup").getValue().toString();

                patientRetName_Tv.setText(name);
                patientRetEmail_Tv.setText(useremail);
                patientRetAge_Tv.setText(age);
                patientRetAddress_Tv.setText(address);
                patientRetPhoneno_Tv.setText(phoneno);
                patientRetEmergencyPh_Tv.setText(emergencyph);
                patientRetRelation_Tv.setText(relation);
                patientRetGender_Tv.setText(gender);
                patientRetBloodGroup_Tv.setText(bloodgroup);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}

