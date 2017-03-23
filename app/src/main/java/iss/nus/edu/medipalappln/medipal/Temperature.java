package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Temperature extends Measurement {

    // Constructor
    public Temperature(Integer ID, Integer temperature, String measuredOn) {
        this.ID = ID;
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = 0;
        this.temperature = temperature;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    public Temperature (Integer temperature, String measuredOn) {
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = 0;
        this.temperature = temperature;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Integer getID() { return this.ID; }

    public Integer getTemperature() { return this.temperature; }

    public String getMeasuredOn() {
        return this.measuredOn;
    }

    public String toString() {
        return this.temperature + " | " +
                this.measuredOn;
    }

    public Temperature getTemperature(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setTemperature(Integer temperature, String measuredOn, Integer ID) {
        this.temperature = temperature;
        this.measuredOn = measuredOn;
        this.ID = ID;
    }
}
