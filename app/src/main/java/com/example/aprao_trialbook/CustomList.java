package com.example.aprao_trialbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * This class is to create the custom Adapter for the list view of our app.
 * It also sets the details for each row (sets information on text views)
 * Also holds the functionality for deleting a row by clicking on the "delete" image.
 */

public class CustomList extends ArrayAdapter<Experiment> {

    //private ArrayList<Experiment> experiments;
    private ExpList experimentList;
    private Context context;

    public CustomList(Context context, ExpList experimentList) {
        super(context,0,experimentList.experiments);
        this.experimentList = experimentList;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }


        Experiment experiment = experimentList.experiments.get(position);
        TextView expDate = view.findViewById(R.id.date_text);
        TextView expDescription = view.findViewById(R.id.description_text);
        TextView expTrials = view.findViewById(R.id.trials_text);
        TextView expRate = view.findViewById(R.id.rate_text);
        ImageView deleteImage =  view.findViewById(R.id.image_delete);
        // setting information on text views for each row of the list view
        expDate.setText(experiment.getDate().toString());
        expDescription.setText(experiment.getDescription());
        expTrials.setText("Trials : " + Integer.toString(experiment.getSuccesses()+ experiment.getFailures() ));
        expRate.setText("Success Rate = " +Double.toString(experiment.calculateRate()));

        //to delete an experiment (entire row)
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                experimentList.removeExperiment(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

}
