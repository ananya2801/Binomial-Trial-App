package com.example.aprao_trialbook;

/**
 * This class deals with all information that is input through dialog boxes/fragments.
 * It is mainly to get information when experiments are edited or added.
 * A calendar is used to input date from the user and this class also has that functionality.
 */

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddExpFragment extends DialogFragment {
    private EditText expDate;
    private EditText expDescription;
    private OnFragmentInteractionListener listener;
    private Experiment experiment;

    public AddExpFragment(){
        this.experiment = null;
    }

    public AddExpFragment(Experiment experiment) {
        this.experiment = experiment;
    }

    public interface OnFragmentInteractionListener {
        void onOkPressed(Experiment newExp);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_exp_fragment_layout, null);
        expDescription = view.findViewById(R.id.expDescrp_editText);
        expDate = view.findViewById(R.id.expDate_editText);

        // to input date through calendar
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String format = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                expDate.setText(sdf.format(calendar.getTime()));
            }
        };

        expDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle("Add/Edit Experiment")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (validate()){
                            String descrp = expDescription.getText().toString();
                            String date = expDate.getText().toString();

                            if (experiment == null) {

                                listener.onOkPressed(new Experiment(date, descrp));
                               }
                            else {
                               experiment.editDetails(date,descrp);
                            }
                        }
                    }}).create();
    }

    // to do input validation and check if user input is appropriate
    private boolean validate(){

        if (expDescription.getText().length() == 0){

            Toast.makeText(getActivity(), "Fields cannot be left blank",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else if (expDate.getText().length() == 0){

            Toast.makeText(getActivity(), "Fields cannot be left blank",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else if(expDescription.getText().length() > 40){
            Toast.makeText(getActivity(), "Description cannot exceed 40 characters",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }

}
