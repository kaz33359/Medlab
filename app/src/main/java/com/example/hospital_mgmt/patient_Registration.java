package com.example.hospital_mgmt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class patient_Registration extends AppCompatActivity implements View.OnClickListener {

    private Button patientRegSignup_Btn;
    private EditText patientRegEmail_Et , patientRegPassword_Et;
    private TextView patientRegLogin_Tv;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__registration);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        patientRegSignup_Btn = (Button) findViewById(R.id.patientregsignup_btn);
        patientRegEmail_Et = (EditText) findViewById(R.id.patientregemail_et);
        patientRegPassword_Et = (EditText) findViewById(R.id.patientregpassword_et);
        patientRegLogin_Tv= (TextView) findViewById(R.id.patientreglogin_tv);

        patientRegSignup_Btn.setOnClickListener(this);
        patientRegLogin_Tv.setOnClickListener(this);
    }

    private void registerPatient(){
        String email = patientRegEmail_Et.getText().toString().trim();
        String pass = patientRegPassword_Et.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this, "Enter an email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //pass is empty
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //reg successfully
                            Toast.makeText(patient_Registration.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(), patient_Dashboard.class));
                        }
                        else
                        {
                            Toast.makeText(patient_Registration.this,"Registraton Unsuccessfull",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (v == patientRegSignup_Btn)
        {
            registerPatient();
        }
        if (v == patientRegLogin_Tv)
        {
            finish();
            startActivity(new Intent(this, patient_Login.class));
        }


    }
}
