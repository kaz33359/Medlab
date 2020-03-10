package com.example.hospital_mgmt;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class doctor_Appointments extends AppCompatActivity {

    private ListView listView;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ArrayList<String> appolist;
    private ArrayAdapter<String> appoadapter;
    private User userAppo;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__appointments);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        userAppo = new User();
        listView = (ListView) findViewById(R.id.doctorappolistView);
        database = FirebaseDatabase.getInstance();
       // ref = database.getReference("Appointments");
        ref = FirebaseDatabase.getInstance().getReference().child("Appointments").child(user.getUid());
        appolist = new ArrayList<String>();
        appoadapter = new ArrayAdapter<String>(this,R.layout.appointmentinfo, R.id.appoinfo, appolist);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    userAppo = ds.getValue(User.class);
                    appolist.add(userAppo.getName().toString() + "   " +  userAppo.getTime().toString());
                }
                listView.setAdapter(appoadapter);

               //  String address = dataSnapshot.child("address").getValue().toString();
                // String contactNum = dataSnapshot.child("contactNum").getValue().toString();
                // String day = dataSnapshot.child("day").getValue().toString();
               //  String year = dataSnapshot.child("year").getValue().toString();
               //  String month = dataSnapshot.child("month").getValue().toString();
               //  String name = dataSnapshot.child("name").getValue().toString();
               //  String time = dataSnapshot.child("time").getValue().toString();

               //  appolist.add(name + " " + time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}