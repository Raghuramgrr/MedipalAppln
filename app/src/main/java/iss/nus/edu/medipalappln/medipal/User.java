package iss.nus.edu.medipalappln.medipal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

<<<<<<< HEAD
import iss.nus.edu.medipalappln.asynTask.AddEmergency;
import iss.nus.edu.medipalappln.asynTask.AddPersonal;
import iss.nus.edu.medipalappln.asynTask.ListEmergency;
import iss.nus.edu.medipalappln.asynTask.ListMeasurement;
import iss.nus.edu.medipalappln.asynTask.ListPersonal;
=======
import iss.nus.edu.medipalappln.asynTask.ListMeasurementTask;
>>>>>>> 37c916e6ffe02fb07aa264da1d8fc64feabd4e01

public class User {

    private String IDNo; //use this as login ID
    private ArrayList<Personal> personalBio;
    //private ArrayList<HealthBio> healthBio;
    //private ArrayList<Medicine> medicine;
    private ArrayList<Measurement> measurements;
    //private ArrayList<Consumption> consumption;
    //private ArrayList<Appointment> appointment;
    private ArrayList<Emergency> emergency;

<<<<<<< HEAD
    private ListMeasurement listMeasurement;
    private ListPersonal listPersonal;
    private ListEmergency listEmergency;
    private AddEmergency addEmergency;
    private AddPersonal addPersonal;
=======
    private ListMeasurementTask listMeasurementTask;

>>>>>>> 37c916e6ffe02fb07aa264da1d8fc64feabd4e01
    public User() {
        personalBio = new ArrayList<Personal>();
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

   public List<Personal> getPersonal (Context context) {
        listPersonal = new ListPersonal(context);
        listPersonal.execute((Void) null);

        try {
            personalBio = listPersonal.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (emergency == null) {
            emergency = new ArrayList<Emergency> ();
        }

        return new ArrayList<Personal>(personalBio);
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
    public Personal addPersonal (int ID, String Name,
                                   String Dob,String Idno,String Address,String Postcode,String Height,String Bloodtype,Context context) {
        //numMembers++;
        Personal em = new Personal (ID,Name,Dob,Idno,Address,Postcode,Height,Bloodtype);
        addPersonal=new AddPersonal(context);
        addPersonal.execute(em);
        return em;
    }


}
