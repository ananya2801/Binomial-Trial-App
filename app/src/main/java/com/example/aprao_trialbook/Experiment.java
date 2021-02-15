package com.example.aprao_trialbook;
/**
 * This class has all the details of the experiment and is extensively used by other classes.
 * It's main purpose is to let the other classes create its objects and manipulate them based on required functions.
 */

import java.io.Serializable;
import java.util.Date;

public class Experiment implements Serializable {

    private String date;
    private String description;
    private int successes;
    private int failures;

    public Experiment(String date, String description) {

        this.date = date;
        this.description = description;
        successes = 0;
        failures = 0;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getSuccesses() {
        return successes;
    }

    public void setSuccesses(int successes) {
        this.successes = successes;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }

    public void increaseSuccess(){
        successes++;
    }

    public void increaseFails(){
        failures++;
    }

    public double calculateRate(){
        double rate = Math.round(((double)successes/(double)(successes+failures)) * 100.00) / 100.00;
        return rate;
    }

    public void editDetails(String newDate, String newDescrp){
        setDate(newDate);
        setDescription(newDescrp);

    }

}
