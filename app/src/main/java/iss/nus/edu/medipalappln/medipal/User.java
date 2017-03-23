package iss.nus.edu.medipalappln.medipal;

import android.content.Context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import iss.nus.edu.medipalappln.asynTask.AddBloodPressureTask;
import iss.nus.edu.medipalappln.asynTask.AddPulseTask;
import iss.nus.edu.medipalappln.asynTask.AddTemperatureTask;
import iss.nus.edu.medipalappln.asynTask.AddWeightTask;
import iss.nus.edu.medipalappln.asynTask.DeleteBloodPressureTask;
import iss.nus.edu.medipalappln.asynTask.DeletePulseTask;
import iss.nus.edu.medipalappln.asynTask.DeleteTemperatureTask;
import iss.nus.edu.medipalappln.asynTask.DeleteWeightTask;
import iss.nus.edu.medipalappln.asynTask.ViewBloodPressureTask;
import iss.nus.edu.medipalappln.asynTask.ViewPulseTask;
import iss.nus.edu.medipalappln.asynTask.ViewTemperatureTask;
import iss.nus.edu.medipalappln.asynTask.ViewWeightTask;

public class User {

    private String IDNo; //use this as login ID
    //private PersonalBio personalBio;
    //private ArrayList<HealthBio> healthBio;
    //private ArrayList<Medicine> medicine;
    private ArrayList<Measurement> measurements;
    private ArrayList<BloodPressure> bloodPressures;
    private ArrayList<Pulse> pulses;
    private ArrayList<Temperature> temperatures;
    private ArrayList<Weight> weights;
    //private ArrayList<Consumption> consumption;
    //private ArrayList<Appointment> appointment;
    //private ArrayList<ICE> ice;

    private ViewBloodPressureTask viewBloodPressureTask;
    private AddBloodPressureTask addBloodPressureTask;
    private DeleteBloodPressureTask deleteBloodPressureTask;

    private ViewPulseTask viewPulseTask;
    private AddPulseTask addPulseTask;
    private DeletePulseTask deletePulseTask;

    private ViewTemperatureTask viewTemperatureTask;
    private AddTemperatureTask addTemperatureTask;
    private DeleteTemperatureTask deleteTemperatureTask;

    private ViewWeightTask viewWeightTask;
    private AddWeightTask addWeightTask;
    private DeleteWeightTask deleteWeightTask;


    public User() {
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        //measurements = new ArrayList<Measurement> ();
        bloodPressures = new ArrayList<BloodPressure>();
        pulses = new ArrayList<Pulse>();
        weights = new ArrayList<Weight>();
        temperatures = new ArrayList<Temperature>();
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        //ice = new ArrayList<ICE> ();
    }

    public User(String IDNo) {
        this.IDNo = IDNo;
        //personalBio = new personalBio();
        //healthBio = new ArrayList<HealthBio> ();
        //medicine = new ArrayList<Medicine> ();
        //measurements = new ArrayList<Measurement> ();
        bloodPressures = new ArrayList<BloodPressure>();
        pulses = new ArrayList<Pulse>();
        weights = new ArrayList<Weight>();
        temperatures = new ArrayList<Temperature>();
        //consumption = new ArrayList<Consumption> ()
        //consumption = new ArrayList<Consumption> ();
        //appointment = new ArrayList<Appointment> ();
        //ice = new ArrayList<ICE> ();
    }

    public String getUserIDNo() {
        return this.IDNo;
    }

    //Measurement: blood pressure
    public BloodPressure getBloodPressure(Integer id) {
        Iterator<BloodPressure> i = bloodPressures.iterator();
        while (i.hasNext()) {
            BloodPressure bp = i.next();
            if (bp.getID() == id) {
                return bp;
            }
        }

        return null;
    }

    public List<BloodPressure> getBloodPressure (Context context) {
        viewBloodPressureTask = new ViewBloodPressureTask(context);
        viewBloodPressureTask.execute((Void) null);

        try {
            bloodPressures = viewBloodPressureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (bloodPressures == null) {
            bloodPressures = new ArrayList<BloodPressure> ();
        }

        return new ArrayList<BloodPressure>(bloodPressures);
    }

    public void addBloodPressure (Context context, Integer systolic, Integer diastolic, String measuredOn) {
        BloodPressure bp = new BloodPressure(systolic, diastolic, measuredOn);
        addBloodPressureTask = new AddBloodPressureTask(context);
        addBloodPressureTask.execute(bp);
    }

    public void deleteBloodPressure (Context context, Integer id) {
        BloodPressure bp = getBloodPressure(id);
        if (bp != null) {
            bloodPressures.remove(bp);
        }
        deleteBloodPressureTask = new DeleteBloodPressureTask(context);
        deleteBloodPressureTask.execute(bp);
    }

    //Measurement: Pulse
    public Pulse getPulse(Integer id) {
        Iterator<Pulse> i = pulses.iterator();
        while (i.hasNext()) {
            Pulse pulse = i.next();
            if (pulse.getID().equals(id)) {
                return pulse;
            }
        }

        return null;
    }

    public List<Pulse> getPulse (Context context) {
        viewPulseTask = new ViewPulseTask(context);
        viewPulseTask.execute((Void) null);

        try {
            pulses = viewPulseTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (pulses == null) {
            pulses = new ArrayList<Pulse> ();
        }

        return new ArrayList<Pulse>(pulses);
    }

    public void addPulse (Context context, Integer pulse, String measuredOn) {
        Pulse p = new Pulse(pulse, measuredOn);
        addPulseTask = new AddPulseTask(context);
        addPulseTask.execute(p);
    }

    public void deletePulse (Context context, Integer id) {
        Pulse p = getPulse(id);
        if (p != null) {
            pulses.remove(p);
        }
        deletePulseTask = new DeletePulseTask(context);
        deletePulseTask.execute(p);
    }

    //Measurement: Temperature
    public Temperature getTemperature(Integer id) {
        Iterator<Temperature> i = temperatures.iterator();
        while (i.hasNext()) {
            Temperature t = i.next();
            if (t.getID().equals(id)) {
                return t;
            }
        }

        return null;
    }

    public List<Temperature> getTemperature (Context context) {
        viewTemperatureTask = new ViewTemperatureTask(context);
        viewTemperatureTask.execute((Void) null);

        try {
            temperatures = viewTemperatureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (temperatures == null) {
            temperatures = new ArrayList<Temperature> ();
        }

        return new ArrayList<Temperature>(temperatures);
    }

    public void addTemperature (Context context, Integer temperature, String measuredOn) {
        Temperature bp = new Temperature(temperature, measuredOn);
        addTemperatureTask = new AddTemperatureTask(context);
        addTemperatureTask.execute(bp);
    }

    public void deleteTemperature (Context context, Integer id) {
        Temperature t = getTemperature(id);
        if (t != null) {
            pulses.remove(t);
        }
        deleteTemperatureTask = new DeleteTemperatureTask(context);
        deleteTemperatureTask.execute(t);
    }


    //Measurement: Weight
    public Weight getWeight(Integer id) {
        Iterator<Weight> i = weights.iterator();
        while (i.hasNext()) {
            Weight w = i.next();
            if (w.getID().equals(id)) {
                return w;
            }
        }

        return null;
    }

    public List<Weight> getWeight (Context context) {
        viewWeightTask = new ViewWeightTask(context);
        viewWeightTask.execute((Void) null);

        try {
            weights = viewWeightTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (weights == null) {
            weights = new ArrayList<Weight> ();
        }

        return new ArrayList<Weight>(weights);
    }

    public void addWeight (Context context, Double weight, String measuredOn) {
        Weight w = new Weight(weight, measuredOn);
        addWeightTask = new AddWeightTask(context);
        addWeightTask.execute(w);
    }

    public void deleteWeight (Context context, Integer id) {
        Weight w = getWeight(id);
        if (w != null) {
            weights.remove(w);
        }
        deleteWeightTask = new DeleteWeightTask(context);
        deleteWeightTask.execute(w);
    }


}
