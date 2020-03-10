package com.example.hospital_mgmt;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class patient_Chat extends AppCompatActivity {

    LinearLayout patient_layout;
    RelativeLayout patient_layout_2;
    ImageView patient_sendButton;
    EditText pat_messageArea;
    ScrollView patient_scrollView;
    Firebase reference1, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__chat);

        patient_layout = (LinearLayout) findViewById(R.id.patient_layout1);
        patient_layout_2 = (RelativeLayout)findViewById(R.id.patient_layout2);
        patient_sendButton = (ImageView)findViewById(R.id.patient_sendButton);
        pat_messageArea = (EditText)findViewById(R.id.patient_messageArea);
        patient_scrollView = (ScrollView)findViewById(R.id.patient_scrollView);

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://hospital-mgmt-1e680.firebaseio.com/doctors" + patientUserDetails.name + "_" + patientUserDetails.chatWith);
        reference2 = new Firebase("https://hospital-mgmt-1e680.firebaseio.com/doctors" + patientUserDetails.chatWith + "_" + patientUserDetails.name);

        patient_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = pat_messageArea.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", patientUserDetails.name);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    pat_messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if(userName.equals(patientUserDetails.name)){
                    addMessageBox("You:-\n" + message, 1);
                }
                else{
                    addMessageBox(patientUserDetails.chatWith + ":-\n" + message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(patient_Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_in);
        }
        else{
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_out);
        }
        textView.setLayoutParams(lp2);
        patient_layout.addView(textView);
        patient_scrollView.fullScroll(View.FOCUS_DOWN);
    }
}