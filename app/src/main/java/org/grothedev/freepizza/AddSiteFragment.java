package org.grothedev.freepizza;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by thomas on 02/08/17.
 */

public class AddSiteFragment extends Fragment {

    Site toAdd;

    EditText foodInput, infoInput, locationInput, startInput, endInput;
    Button submitButton;
    DatePicker dayInput;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.add_site_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUIElements();
    }

    private void initUIElements(){
        foodInput = (EditText) getView().findViewById(R.id.editTextFood);
        infoInput = (EditText) getView().findViewById(R.id.editTextInfo);
        locationInput = (EditText) getView().findViewById(R.id.editTextLocation);
        dayInput = (DatePicker) getView().findViewById(R.id.datePicker);
        startInput = (EditText) getView().findViewById(R.id.editTextStartTime);
        endInput = (EditText) getView().findViewById(R.id.editTextEndTime);

        submitButton = (Button) getView().findViewById(R.id.buttonSubmit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //any validation necessary here or ok to deal with on server?
                Log.d("date", dayInput.toString());
                toAdd = new Site(foodInput.getText().toString(),
                                infoInput.getText().toString(),
                                locationInput.getText().toString(),
                                "placeholder", //day input here
                                startInput.getText().toString(),
                                endInput.getText().toString());

                //onSubmitListener.onSubmitButtonPressed(toAdd); why did i do it this way and not asynctask?

                new AddSiteTask().execute(toAdd);
            }
        });
    }
}
