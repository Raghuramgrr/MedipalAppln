package iss.nus.edu.medipalappln.medipal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.edu.medipalappln.asynTask.ListEmergency;
import iss.nus.edu.medipalappln.asynTask.ListMeasurement;
import iss.nus.edu.medipalappln.asynTask.AddEmergency;

public class User {

    private String IDNo; //use this as login ID
    //private PersonalBio personalBio;
    //private ArrayList<HealthBio> healthBio;
    //private ArrayList<Medicine> medicine;
    private ArrayList<Measurement> measurements;
    //private ArrayList<Consumption> consumption;
    //private ArrayList<Appointment> appointment;
    private ArrayList<Emergency> emergency;

    private ListMeasurement listMeasurement;
    private ListEmergency listEmergency;
    private AddEmergency addEmergency;

    public User() {
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurements = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        emergency = new ArrayList<Emergency> ();
    }

    public User(String IDNo) {
        this.IDNo = IDNo;
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        measurements = new ArrayList<Measurement> ();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        emergency = new ArrayList<Emergency> ();
    }

    public String getUserIDNo() {
        return this.IDNo;
    }

    public List<Measurement> getMeasurements (Context context) {
        listMeasurement = new ListMeasurement(context);
        listMeasurement.execute((Void) null);

        try {
            measurements = listMeasurement.get();
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

    public List<Emergency> getEmergency (Context context) {
        listEmergency = new ListEmergency(context);
        listEmergency.execute((Void) null);

        try {
            emergency = listEmergency.get();
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
    public Emergency addEmergency (int ID, String Name,
                             String Phone,String Priority,String Desc) {
        //numMembers++;
        Emergency m = new Emergency (ID,Name,Phone,Priority,Desc);
        emergency.add (m);
        return m;
    }
    public Emergency addEmergency (int ID, String Name,
                                   String Phone,String Priority,String Desc,Context context) {
        //numMembers++;
        Emergency em = new Emergency (ID,Name,Phone,Priority,Desc);
        addEmergency=new AddEmergency(context);
        addEmergency.execute(em);
        return em;
    }


}
