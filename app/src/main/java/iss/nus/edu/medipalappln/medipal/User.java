package iss.nus.edu.medipalappln.medipal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.edu.medipalappln.asynTask.ListMeasurementTask;

public class User {

    private String IDNo; //use this as login ID
    //private PersonalBio personalBio;
    //private ArrayList<HealthBio> healthBio;
    //private ArrayList<Medicine> medicine;
    private ArrayList<Measurement> measurements;
    //private ArrayList<Consumption> consumption;
    //private ArrayList<Appointment> appointment;
    //private ArrayList<ICE> ice;

    private ListMeasurementTask listMeasurementTask;

    public User() {
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurements = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        //ice = new ArrayList<ICE> ();
    }

    public User(String IDNo) {
        this.IDNo = IDNo;
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurements = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        //ice = new ArrayList<ICE> ();
    }

    public String getUserIDNo() {
        return this.IDNo;
    }

    public List<Measurement> getMeasurements (Context context) {
        listMeasurementTask = new ListMeasurementTask(context);
        listMeasurementTask.execute((Void) null);

        try {
            measurements = listMeasurementTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (measurements == null) {
            measurements = new ArrayList<Measurement> ();
        }

        return new ArrayList<Measurement>(measurements);
    }
}
