package org.grothedev.freepizza;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.Time;

/**
 * Created by thomas on 06/08/17.
 */

public class TimePickerFragment extends DialogFragment {

    String timeStr;
    TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Time now = new Time();
        now.setToNow();

        timeSetListener = (TimePickerDialog.OnTimeSetListener) getActivity();

        return new TimePickerDialog(getActivity(), timeSetListener, now.hour, now.minute, false);
    }
}
