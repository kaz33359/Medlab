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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class doctor_Registration extends AppCompatActivity implements View.OnClickListener {

    private Button doctorRegSignup_Btn;
    private EditText doctorRegEmail_Et , doctorRegPassword_Et;
    private TextView doctorRegLogin_Tv;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__registration);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        doctorRegSignup_Btn = (Button) findViewById(R.id.doctorregsignup_btn);
        doctorRegEmail_Et = (EditText) findViewById(R.id.doctorregemail_et);
        doctorRegPassword_Et = (EditText) findViewById(R.id.doctorregpassword_et);
        doctorRegLogin_Tv = (TextView) findViewById(R.id.doctorreglogin_tv);

        doctorRegSignup_Btn.setOnClickListener(this);
        doctorRegLogin_Tv.setOnClickListener(this);
    }

    private void registerPatient(){
        String email = doctorRegEmail_Et.getText().toString().trim();
        String pass = doctorRegPassword_Et.getText().toString().trim();

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
                            Toast.makeText(doctor_Registration.this,"Registered Successfully",Toast.LENGTH_LONG).show();

                            finish();
                            startActivity(new Intent(getApplicationContext(), doctor_Dashboard.class));
                        }
                        else
                        {
                            Toast.makeText(doctor_Registration.this,"Registraton Unsuccessfull",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (v == doctorRegSignup_Btn)
        {
            registerPatient();
        }
        if (v == doctorRegLogin_Tv)
        {
            finish();
            startActivity(new Intent(this, doctor_Login.class));
        }


    }
}

