package com.example.aprao_trialbook;

/**
 * This is the main First activity of the app. It creates the listview adapter and sets its contents from the
 * experiment list which is received from expList class. (To separate model and GUI)
 * It also holds the functionality for adding a new experiment when the floating action button is clicked
 * Also holds functionality for Long pressing on a row : Able to edit details of the row
 * Lastly, on simply clicking a row: handles transfer to second activity through an intent
 */

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements AddExpFragment.OnFragmentInteractionListener,Serializable {

    ListView expList;
    ArrayAdapter<Experiment> expAdapter;
    ExpList expDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expList = findViewById(R.id.experiment_list);
        expDataList = new ExpList();

        expAdapter = new CustomList(this, expDataList);

        expList.setAdapter(expAdapter);

        // to add an experiment
        final FloatingActionButton addExpButton = findViewById(R.id.add_experiment_button);
        addExpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddExpFragment().show(getSupportFragmentManager(), "ADD_EXPERIMENT");
            }
        });

        // to go to second activity which allows recording of successes and failures
        expList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ExpDetails.class );
                Experiment experimentObj = expDataList.experiments.get(position);
                intent.putExtra("experiment", experimentObj);
                intent.putExtra("position",position);
                startActivityForResult(intent,1);

            }
        });

        // to edit an experiment
        expList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AddExpFragment(expDataList.experiments.get(position)).show(getSupportFragmentManager(), "EDIT_EXPERIMENT");
                return false;
            }
        });
    }

    // to add an experiment
    @Override
    public void onOkPressed(Experiment newExperiment) {
        expDataList.addExperiment(newExperiment);
        expAdapter.notifyDataSetChanged();

    }

    // getting information back from second activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Experiment result= (Experiment) data.getSerializableExtra("result");
                int position = data.getIntExtra("position",0);
                expDataList.experiments.set(position,result);
                expAdapter.notifyDataSetChanged();

            }
        }
    }
}