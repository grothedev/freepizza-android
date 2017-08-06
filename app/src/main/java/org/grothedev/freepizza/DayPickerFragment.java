package org.grothedev.freepizza;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by thomas on 05/08/17.
 */

public class DayPickerFragment extends DialogFragment{

    public String dayStr; //string representing the date, to be sent to server
    public Site toAdd;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        dateSetListener = (DatePickerDialog.OnDateSetListener) getActivity();

        return new DatePickerDialog(getActivity(), dateSetListener, year, month+1, day); //adding 1 to month because starts at 0
    }

}
