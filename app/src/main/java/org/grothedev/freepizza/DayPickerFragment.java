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

public class DayPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    public String dayStr; //string representing the date, to be sent to server
    public Site toAdd;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        dayStr = year + "-" + month + "-" + day;
    }
}
