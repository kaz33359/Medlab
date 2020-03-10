package com.example.hospital_mgmt;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import static com.prolificinteractive.materialcalendarview.CalendarDay.today;

//TODO ADD BACKSTACK TO BE ABLE TO USE BACK BUTTON
public class AvailabilityActivity extends AppCompatActivity implements OnDateSelectedListener{


    MaterialCalendarView myCalendar;
    AvailabilityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        fragment = new AvailabilityFragment();

        myCalendar = (MaterialCalendarView) findViewById(R.id.calendarView);

        int maxYear = today().getYear();
        int maxMonth = today().getMonth();
        int maxDay = today().getDay();

        myCalendar.state().edit()
                .setMinimumDate(today())
                .setMaximumDate(CalendarDay.from(maxYear, maxMonth + 1, maxDay))//format year,month, day
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        myCalendar.setOnDateChangedListener(this);

        Toast.makeText(this, "Select a day to view availability", Toast.LENGTH_SHORT).show();
    }

    //WHEN USER CLICKS ON A DAY DISPLAY A FRAGMENT`
    @Override
    public void onDateSelected(MaterialCalendarView myCalendar, CalendarDay date, boolean selected) {
        String Year = String.valueOf(date.getYear());
        String Month = String.valueOf(date.getMonth());
        String Day = String.valueOf(date.getDay());

        Bundle bundle = new Bundle();
        bundle.putString("Month",Month);
        bundle.putString("Day", Day);
        bundle.putString("Year", Year);

       if (fragment.getView() == null) {
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
            Log.i("Availability if", "the fragment is null");}
       else {

           fragment.getArguments().putString("Month",Month);
            fragment.getArguments().putString("Day",Day);
            fragment.getArguments().putString("Year", Year);
           FragmentManager fragmentManager = getSupportFragmentManager();
           fragmentManager.beginTransaction()
                  .detach(fragment)
                   .attach(fragment)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
           Log.i("Availability if", "the fragment is null");
       }
    }//End on date selected

}

