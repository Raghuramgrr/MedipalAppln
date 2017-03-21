package iss.nus.edu.medipalappln.medipal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.edu.medipalappln.asynTask.ListMeasurement;
import iss.nus.edu.medipalappln.asynTask.ListEmergency;

public class User {

    private String IDNo; //use this as login ID
    //private PersonalBio personalBio;
    //private ArrayList<HealthBio> healthBio;
    //private ArrayList<Medicine> medicine;
    private ArrayList<Measurement> measurement;
    //private ArrayList<Consumption> consumption;
    //private ArrayList<Appointment> appointment;
    private ArrayList<Emergency> emergency;

    private ListMeasurement listMeasurement;
    private ListEmergency listEmergency;

    public User() {
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurement = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        emergency = new ArrayList<Emergency> ();
    }

    public User(String IDNo) {
        this.IDNo = IDNo;
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurement = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        emergency = new ArrayList<Emergency> ();
    }

    public List<Measurement> getMeasurements (Context context) {
        listMeasurement = new ListMeasurement(context);
        listMeasurement.execute((Void) null);

        try {
            listMeasurement.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (measurement == null) {
            measurement = new ArrayList<Measurement> ();
        }

        return new ArrayList<Measurement>(measurement);
    }
    public List<Emergency> getPhoneNumber (Context context) {
        listEmergency = new ListEmergency(context);
        listEmergency.execute((Void) null);

        try {
            listEmergency.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (emergency == null) {
            emergency = new ArrayList<Emergency> ();
        }

        return new ArrayList<Emergency>(emergency);
    }
}
