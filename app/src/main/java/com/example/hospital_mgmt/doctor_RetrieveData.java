package com.example.hospital_mgmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doctor_RetrieveData extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView doctorRetName_Tv, doctorRetEmail_Tv, doctorRetAge_Tv, doctorRetAddress_Tv, doctorRetPhone_Tv, doctorRetGender_Tv, doctorRetQualification_Tv, doctorRetHospital_Tv;
    private Button DoctorUpdsteProfile_Btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__retrieve_data);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        final String docemail = user.getEmail();

        doctorRetName_Tv = (TextView) findViewById(R.id.doctorretname_tv);
        doctorRetAge_Tv = (TextView) findViewById(R.id.doctorretage_tv);
        doctorRetEmail_Tv = (TextView) findViewById(R.id.doctorretemali_tv);
        doctorRetAddress_Tv = (TextView) findViewById(R.id.doctorretaddress_tv);
        doctorRetPhone_Tv = (TextView) findViewById(R.id.doctorretphoneno_tv);
        doctorRetGender_Tv = (TextView) findViewById(R.id.doctorretgender_tv);
        doctorRetQualification_Tv = (TextView) findViewById(R.id.doctorretqualification_tv);
        doctorRetHospital_Tv = (TextView) findViewById(R.id.doctorrethospital_tv);
        //DoctorUpdsteProfile_Btn = (Button) findViewById(R.id.doctorupdateprofile_btn);
        //DoctorUpdsteProfile_Btn.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("doctors").child(user.getDisplayName());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                //String email = dataSnapshot.child("email").getValue().toString();
                String age = dataSnapshot.child("age").getValue().toString();
                String phoneno = dataSnapshot.child("phoneno").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
                String gender = dataSnapshot.child("gender").getValue().toString();
                String qualification = dataSnapshot.child("qualification").getValue().toString();
                String hospital = dataSnapshot.child("hospital").getValue().toString();

                doctorRetName_Tv.setText(name);
                doctorRetAge_Tv.setText(age);
                doctorRetAddress_Tv.setText(address);
                doctorRetEmail_Tv.setText(docemail);
                doctorRetPhone_Tv.setText(phoneno);
                doctorRetGender_Tv.setText(gender);
                doctorRetQualification_Tv.setText(qualification);
                doctorRetHospital_Tv.setText(hospital);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}
