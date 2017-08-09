package org.grothedev.freepizza;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by thomas on 03/08/17.
 */

public class AddSiteActivity extends Activity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Site toAdd;

    EditText foodInput, infoInput, locationInput;
    Button submitButton;
    DatePicker dayInput;
    Button dayButton, startTimeButton, endTimeButton;
    ProgressBar progress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        toAdd = new Site();

        initUIElements();
    }

    private void initUIElements(){
        foodInput = (EditText) findViewById(R.id.editTextFood);
        infoInput = (EditText) findViewById(R.id.editTextInfo);
        locationInput = (EditText) findViewById(R.id.editTextLocation);
        dayInput = (DatePicker) findViewById(R.id.datePicker);

        dayButton = (Button) findViewById(R.id.buttonChooseDay);
        startTimeButton = (Button) findViewById(R.id.buttonChooseStart);
        endTimeButton = (Button) findViewById(R.id.buttonChooseEnd);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setVisibility(View.GONE);
        submitButton = (Button) findViewById(R.id.buttonSubmit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //any validation necessary here or ok to deal with on server?

                if(toAdd.start != null && toAdd.end != null && toAdd.day != null){
                    toAdd.food = foodInput.getText().toString();
                    toAdd.info = infoInput.getText().toString();
                    toAdd.location = locationInput.getText().toString();


                    progress.setVisibility(View.VISIBLE);
                    addSite();
                } else {
                    toast("you must set a date, start time, and end time");
                }
            }
        });
    }

    private void addSite(){
        new AddSiteTask().execute(toAdd, progress, this);
    }

    //shows the dialog to choose the day from calendar
    public void showDatePicker(View v){
        DialogFragment dayPickerFragment = new DayPickerFragment();

        dayPickerFragment.show(getFragmentManager(), "daypicker");

    }

    public void showStartTimePicker(View v){
        TimePickerFragment startTimePickerFragment = new TimePickerFragment();
        startTimePickerFragment.show(getFragmentManager(), "startTimePicker");
    }
    public void showEndTimePicker(View v){
        TimePickerFragment endTimePickerFragment = new TimePickerFragment();
        endTimePickerFragment.show(getFragmentManager(), "endTimePicker");

    }


    //setting the date and times from result from date and time picker dialogs
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String dayStr = year + "-" + month + "-" + day;
        toAdd.day = dayStr;
        dayButton.setText("Day: " + dayStr);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        String timeStr = h +":"+m+":00";
        if (getFragmentManager().findFragmentByTag("startTimePicker") != null){
            toAdd.start = timeStr;
            startTimeButton.setText("From: " + timeStr);
        }else {
            toAdd.end = timeStr;
            endTimeButton.setText("To: " + timeStr);
        }
    }


    public void toast(String s){
        Toast t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }

    public static void toast(String s, Activity a){
        Toast t = Toast.makeText(a.getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}
