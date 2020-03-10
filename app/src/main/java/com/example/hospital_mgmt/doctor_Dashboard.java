package com.example.hospital_mgmt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class doctor_Dashboard extends AppCompatActivity implements View.OnClickListener {

    private Button doctorlogout_Btn,doctorprofle_Btn, doctorviewprofile_Btn, viewappointments_Btn, doctorChat_Btn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__dashboard);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, Welcome_Screen.class));
        }


        doctorlogout_Btn = (Button) findViewById(R.id.doctorlogout_btn);
        doctorprofle_Btn = (Button) findViewById(R.id.doctorprofile_btn);
        doctorviewprofile_Btn = (Button) findViewById(R.id.doctorviewprofile_btn);
        doctorChat_Btn = (Button) findViewById(R.id.doctorchat_btn);
        viewappointments_Btn = (Button) findViewById(R.id.viewAppointments_btn);
        doctorlogout_Btn.setOnClickListener(this);
        doctorprofle_Btn.setOnClickListener(this);
        doctorviewprofile_Btn.setOnClickListener(this);
        viewappointments_Btn.setOnClickListener(this);
        doctorChat_Btn.setOnClickListener(this);

    }
    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, Welcome_Screen.class));
        Toast.makeText(doctor_Dashboard.this, "Logged Out",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {

        if (v == doctorprofle_Btn )
        {

            startActivity(new Intent(this, doctor_Profile.class));
        }
        if (v == doctorviewprofile_Btn )
        {

            startActivity(new Intent(this, doctor_RetrieveData.class));
        }
        if (v == doctorlogout_Btn )
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Welcome_Screen.class));
        }
        if (v == viewappointments_Btn )
        {
            startActivity(new Intent(this, doctor_Appointments.class));
        }
        if (v == doctorChat_Btn)
        {
            startActivity(new Intent(this, ChatMain.class));
        }

    }
}