package com.example.hospital_mgmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.hospital_mgmt.R.layout.fragment_availability;

/**
 * A simple {@link Fragment} subclass.
 */
    //TODO 1. PREVENT FROM CHECKING DATABASE IF NOT CONNECTED TO THE INTERNET

public class AvailabilityFragment extends Fragment {


    private CustomAdapter arrayAdapter;
    //THIS CONTROLS THE LIST TIMES
    private String[] time = new String[] {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
            "4:00 PM", "5:00 PM"};
    private ChildEventListener mChildEventListener;
    public List<Appointment> Appointmentlist = new ArrayList<>();
    private DatabaseReference mAppointmentsDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;


    Appointment value;

    public AvailabilityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //GETS THE INFORMATION FROM ACTIVITY
        final String myMonth = getArguments().getString("Month");
        final String myDay = getArguments().getString("Day");//Pass this to the array adapter
        final String myYear = getArguments().getString("Year");


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        //DATABASE INFO
        mAppointmentsDatabaseReference = mFirebaseDatabase.getReference().child("Appointments");
        mAppointmentsDatabaseReference.keepSynced(true);


        View rootView = inflater.inflate(fragment_availability, container, false);

        Appointmentlist.clear();

        //CHILD LISTENER- THIS IS USED TO ADD TO THE LIST APPOINTMENTS USING THE CRITERIA SPECIFIED
        //BY THE USER.
        mChildEventListener = new ChildEventListener() {
            @Override
            //call whenever a message is inserted to the list. will update for every child
            //already on list
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                    value = dataSnapshot.getValue(Appointment.class);

                    if (value.getMonth().equals(myMonth) && value.getYear().equals(myYear)) {
                        Appointmentlist.add(value);

                    }
                    arrayAdapter.notifyDataSetChanged();


                    Log.i("In evnt list size", String.valueOf(Appointmentlist.size()));//GOES HERE LAST  4

            }
            @Override
            //CALLED WHEN AN EXISTING ITEM GETS CHANGED
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            //CALLED WHEN AN EXISTING ITEM GETS DELETED
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            //CALLED WHEN SOMETHING CHANGED POSITION ON THE LIST
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            //SOMETHING WENT WRONG WHEN TRYING TO MAKE CHANGES
            public void onCancelled(DatabaseError databaseError) {
            }
        };//end child event listener


        mAppointmentsDatabaseReference.
                orderByChild("day").equalTo(myDay).addChildEventListener(mChildEventListener);


        Log.i("In frag b4 adap size", String.valueOf(Appointmentlist.size()));

        //ARRAYADAPTER TO DISPLAY LIST
        arrayAdapter = new CustomAdapter(getActivity(),Appointmentlist,myMonth,myDay,myYear,time);

        ListView myListView = (ListView) rootView.findViewById(R.id.listView1hrs);
        myListView.setAdapter(arrayAdapter);

        myListView.setSelection(6);
        myListView.smoothScrollToPosition(0);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                //Retrieves the value of the item in the clicked position. CHECKS IF THERE
                //IS CONFLICT
                String item = (String) adapter.getItemAtPosition(position);

                 boolean found = false;
                for (Appointment value :  Appointmentlist) {

                    if (value.getTime().trim().equals(item)) {

                         found = true;
                        Toast.makeText(getContext(),"Unavailable. Please choose another time", Toast.LENGTH_SHORT).show();
                    }
                }  //end for loop

                if (!found){

                        Intent intent = new Intent(getActivity(), BookingActivity.class);
                        intent.putExtra("Month", myMonth);
                        intent.putExtra("Day", myDay);
                        intent.putExtra("Year", myYear);
                        intent.putExtra("Time", item.trim());
                        startActivity(intent);


                }//end if

            }//END ON ITEMCLICK

        });//END ON CLICK LISTENER

         return rootView;
    } //END ON CREATE


}//END CLASS
