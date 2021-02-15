package com.example.aprao_trialbook;
/**
 * This class is the second activity of our app which is reached through the main activity using an intent.
 * Information about the experiment is set here.
 * The number of successes and failures can be recorded by clicking on buttons.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExpDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_details);


        Bundle intents = getIntent().getExtras();
        Experiment experiment = (Experiment) getIntent().getSerializableExtra("experiment");
        int position = intents.getInt("position");

        String exp_date = experiment.getDate();
        String exp_descrip = experiment.getDescription();
        int successes = experiment.getSuccesses();
        int failures = experiment.getFailures();

        // getting textview ids and setting information on them
        TextView descTextView = findViewById(R.id.desc_textView);
        descTextView.setText(exp_descrip);

        TextView dateTextView = findViewById(R.id.date_textView);
        dateTextView.setText(exp_date);


        TextView successTextView = findViewById(R.id.success_textView);
        TextView failureTextView = findViewById(R.id.failure_textView);
        TextView rateTextView = findViewById(R.id.successRate_textView);

        successTextView.setText("Successes: "+ Integer.toString(successes));
        failureTextView.setText( "Failures: "+ Integer.toString(failures));
        rateTextView.setText("Success Rate: "+ Double.toString(experiment.calculateRate()));

        // on clicking success button
        Button successButton = (Button)findViewById(R.id.success_button);
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                experiment.increaseSuccess();
                successTextView.setText("Successes: "+Integer.toString(experiment.getSuccesses()));
                rateTextView.setText("Success Rate: "+Double.toString(experiment.calculateRate()));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", experiment);
                returnIntent.putExtra("position",position);
                setResult(Activity.RESULT_OK, returnIntent);

            }
        });

        // on clicking failure button
        Button failureButton = (Button)findViewById(R.id.failure_button);
        failureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                experiment.increaseFails();
                failureTextView.setText("Failures: "+Integer.toString(experiment.getFailures()));
                rateTextView.setText("Success Rate: "+Double.toString(experiment.calculateRate()));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", experiment);
                returnIntent.putExtra("position",position);
                setResult(Activity.RESULT_OK, returnIntent);

            }
        });

    }

}


