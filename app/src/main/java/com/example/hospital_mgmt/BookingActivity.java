package com.example.hospital_mgmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BookingActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mAppointmentsDatabaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    Appointment appointment = new Appointment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //NEW FIREBASE INFO
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mAppointmentsDatabaseReference = mFirebaseDatabase.getReference().child("Appointments").child(firebaseUser.getUid());


        //GETS INFO FROM INTENT
        final String myDay = getIntent().getStringExtra("Day");//Pass this to the array adapter
        final String myYear = getIntent().getStringExtra("Year");
        final String myMonth = getIntent().getStringExtra("Month");//Pass this to the array adapter
        final String myTime = getIntent().getStringExtra("Time");
        Log.i("INSIDE BOOKING ACTIVITY", myDay + " " + myMonth +  " " + myYear + " "+ myTime);

        //ALLOWS USER TO ENTER INFO
        final Button mybutton = (Button) findViewById(R.id.button1);
        EditText name = (EditText) findViewById(R.id.name_input);
        EditText desease = (EditText) findViewById(R.id.address_input);
        EditText number = (EditText) findViewById(R.id.phone_numb_input);
        EditText doctor = (EditText) findViewById(R.id.doctor_input);
        EditText hospital = (EditText) findViewById(R.id.hospital_input);


         name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    appointment.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        desease.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    appointment.setDesease(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    appointment.setContactNum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        doctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                appointment.setDoctor(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        hospital.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                appointment.setHospital(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        //SEND TO THE DATABASE
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                appointment.setMonth(myMonth);
                appointment.setDay(myDay);
                appointment.setYear(myYear);
                appointment.setTime(myTime);

                mAppointmentsDatabaseReference.push().setValue(appointment);

                Toast.makeText( getApplicationContext(),
                        "Appointment booked",
                        Toast.LENGTH_LONG).show();

                startActivity(new Intent(BookingActivity.this, AvailabilityActivity.class));

            }
        });//END ON CLICK LISTENER

    }//End on create

}//end class


