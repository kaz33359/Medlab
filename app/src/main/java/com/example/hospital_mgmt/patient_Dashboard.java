package com.example.hospital_mgmt;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class patient_Dashboard extends AppCompatActivity implements View.OnClickListener {

    private Button patientLogout_Btn, patientUpdateProfle_Btn, patientViewProfile_Btn, patientBookAppointment, patientChats;
    private EditText search_Btn;
  //  private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    private FirebaseAuth firebaseAuth;

   // ArrayList<String> nameList;
   // ArrayList<String> specialisationList;
   // ArrayList<String> hospitalList;

   // SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__dashboard);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, Welcome_Screen.class));
        }


        patientLogout_Btn = (Button) findViewById(R.id.patientlogout_btn);
        patientUpdateProfle_Btn = (Button) findViewById(R.id.patientupdateprofile_btn);
        patientViewProfile_Btn = (Button) findViewById(R.id.patientviewprofile_btn);
        patientBookAppointment = (Button) findViewById(R.id.bookappointments_btn);
        patientChats = (Button) findViewById(R.id.patientchat_btn);
        patientLogout_Btn.setOnClickListener(this);
        patientUpdateProfle_Btn.setOnClickListener(this);
        patientViewProfile_Btn.setOnClickListener(this);
        patientBookAppointment.setOnClickListener(this);
        patientChats.setOnClickListener(this);
       // search_Btn = (EditText) findViewById(R.id.search_btn);
     //   recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

     //   recyclerView.setHasFixedSize(true);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
     //   recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

       // nameList = new ArrayList<>();
      //  specialisationList = new ArrayList<>();
       // hospitalList = new ArrayList<>();

     //   search_Btn.addTextChangedListener(new TextWatcher() {
      //      @Override
      //      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      //      }

       //     @Override
       //     public void onTextChanged(CharSequence s, int start, int before, int count) {

       //     }

         //   @Override
         //   public void afterTextChanged(Editable s) {

       //         if ( !s.toString().isEmpty()){
        //            setAdapter(s.toString());
        //        } else {
        //            nameList.clear();
         //           specialisationList.clear();
         //           hospitalList.clear();
          //          recyclerView.removeAllViews();

         //       }

       //     }

        //    private void setAdapter(final String searchedString) {



        //        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
        //            @Override
        //            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



         //               int counter = 0;

        //                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

         //                   String uid = snapshot.getKey();
         //                   String name = snapshot.child("name").getValue(String.class);
           //                 String specialisation = snapshot.child("specialisation").getValue(String.class);
             //               String hospital = snapshot.child("hospital").getValue(String.class);
//
  //                          if (specialisation.toLowerCase().contains(searchedString.toLowerCase())){
    //                            specialisationList.add(specialisation);
      //                          hospitalList.add(hospital);
        //                        nameList.add(name);
          //                      counter++;

//                            } else if (hospital.toLowerCase().contains(searchedString.toLowerCase())){
  //                              specialisationList.add(specialisation);
    //                            hospitalList.add(hospital);
      //                          nameList.add(name);
        //                        counter++;

          //                  }
        //
        //                  if (counter == 15){
         //                       break;
           //                 }

             //               searchAdapter = new SearchAdapter(patient_Dashboard.this, nameList, specialisationList, hospitalList);
               //             recyclerView.setAdapter(searchAdapter);

//                        }
  //                  }

    //                @Override
      //              public void onCancelled(@NonNull DatabaseError databaseError) {

        //            }
          //      }
            //    );
        //    }
      //  });

    }

    @Override
    public void onClick(View v) {

        if (v == patientUpdateProfle_Btn )
        {
            startActivity(new Intent(this, patient_Profile.class));
        }
        if (v == patientLogout_Btn )
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Welcome_Screen.class));
        }
        if (v == patientViewProfile_Btn )
        {
            startActivity(new Intent(this, patient_RetrieveData.class));
        }
        if (v == patientBookAppointment )
        {
            startActivity(new Intent(this, AvailabilityActivity.class));
        }
        if (v == patientChats )
        {
            startActivity(new Intent(this, patient_ChatMain.class));
        }

    }
}