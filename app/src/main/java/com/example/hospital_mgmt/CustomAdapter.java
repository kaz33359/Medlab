package com.example.hospital_mgmt;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Mabel on 8/10/17.
 */
    //TODO 1: POPULATE TIME
    //TODO 2: ACCESS LIST RECEIVED AND COMPARE TIME WITH THE TIME ARRAY IF MATCH CODE A CERTAIN COLOR
public class CustomAdapter extends ArrayAdapter<String> {

    //NEW 8/13
    private String theMonth;
    private String theDay;
    private String theYear;
    private String[] theTime;
    private List<Appointment> mylist;

    //NEW 8/14
    public CustomAdapter(Activity context, List<Appointment> appointments, String month, String day, String year, String[] time) {
        super(context, 0,time);
        theMonth = month;
        theDay = day;
        theYear = year;
        mylist = appointments;
        theTime=time;
        Log.i("In adapter list size", String.valueOf(mylist.size()));//SECOND
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //TIME TEXTVIEW
        TextView theTextView= (TextView) listItemView.findViewById(R.id.text1);
        theTextView.setText(theTime[position]);

        //AVAILABILITY TEXTVIEW
        TextView theTextView2 = (TextView) listItemView.findViewById(R.id.text2);
        theTextView2.setBackgroundColor(Color.TRANSPARENT);

            //IF ITEM IS BOOKED USE A DIFFERENT COLOR
           if(mylist.size() > 0) {
            Iterator<Appointment> myiterator = mylist.iterator();
            while (myiterator.hasNext()) {
                if (myiterator.next().getTime().equals(theTime[position].trim())) {
                    //theTextView2.setBackgroundColor(Color.TRANSPARENT);
                    theTextView2.setBackgroundColor(Color.MAGENTA);
                }

            }//End while loop
        }//End if

        return listItemView;


    }

}




