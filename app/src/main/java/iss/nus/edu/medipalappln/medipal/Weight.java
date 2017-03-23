package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Weight extends Measurement {

    // Constructor
    public Weight(Integer ID, Double weight, String measuredOn) {
        this.ID = ID;
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = 0;
        this.temperature = 0;
        this.weight = weight;
        this.measuredOn = measuredOn;
    }

    public Weight (Double weight, String measuredOn) {
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = 0;
        this.temperature = 0;
        this.weight = weight;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Integer getID() { return this.ID; }

    public Double getWeight() { return this.weight; }

    public String getMeasuredOn() {
        return this.measuredOn;
    }

    public String toString() {
        return this.weight + " | " +
                this.measuredOn;
    }

    public Weight getWeight(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setWeight(Double weight, String measuredOn, Integer ID) {
        this.weight = weight;
        this.measuredOn = measuredOn;
        this.ID = ID;
    }
}
