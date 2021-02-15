package com.example.aprao_trialbook;

/**
 * This class is mainly to separate the GUI and modelling logic of the program.
 * Instead of directly creating the ArrayList in the MainActivity, it is created here and called by the MainAcitivity
 * This class has functionality to add and delete experiments
 */

import java.util.ArrayList;

public class ExpList {

    ArrayList<Experiment> experiments;

    public ExpList()
    {
        experiments = new ArrayList<Experiment>();
    }

    public void removeExperiment(int position){
        experiments.remove(position);

    }

    public void addExperiment(Experiment experiment)
    {
        experiments.add(experiment);
    }
}
