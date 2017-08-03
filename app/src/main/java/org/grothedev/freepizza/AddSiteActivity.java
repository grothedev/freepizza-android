package org.grothedev.freepizza;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Created by thomas on 03/08/17.
 */

public class AddSiteActivity extends Activity {

    Site toAdd;

    EditText foodInput, infoInput, locationInput;
    Button submitButton;
    DatePicker dayInput;
    TimePicker startInput, endInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);

        initUIElements();
    }

    private void initUIElements(){
        foodInput = (EditText) findViewById(R.id.editTextFood);
        infoInput = (EditText) findViewById(R.id.editTextInfo);
        locationInput = (EditText) findViewById(R.id.editTextLocation);
        dayInput = (DatePicker) findViewById(R.id.datePicker);
        startInput = (TimePicker) findViewById(R.id.timePickerStart);
        endInput = (TimePicker) findViewById(R.id.timePickerEnd);


        submitButton = (Button) findViewById(R.id.buttonSubmit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //any validation necessary here or ok to deal with on server?

                //formatting date and time
                String day = dayInput.getYear() + "-" + dayInput.getMonth() + "-" + dayInput.getDayOfMonth();
                String startTime = startInput.getCurrentHour() + ":" + startInput.getCurrentMinute() + ":00";
                String endTime = endInput.getCurrentHour() + ":" + endInput.getCurrentMinute() + ":00";

                toAdd = new Site(foodInput.getText().toString(),
                        infoInput.getText().toString(),
                        locationInput.getText().toString(),
                        day,
                        startTime,
                        endTime);

                new AddSiteTask().execute(toAdd);
            }
        });
    }



}
