package com.example.hospital_mgmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class test extends AppCompatActivity implements View.OnClickListener {

    private Button doctor_Btn , patient_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);

        doctor_Btn = (Button) findViewById(R.id.doctor_btn);
        patient_Btn = (Button) findViewById(R.id.patient_btn);

        doctor_Btn.setOnClickListener(this);
        patient_Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (v == doctor_Btn)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),doctor_Dashboard.class));
        }
        if (v == patient_Btn)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),patient_Dashboard.class));
        }

    }

}
