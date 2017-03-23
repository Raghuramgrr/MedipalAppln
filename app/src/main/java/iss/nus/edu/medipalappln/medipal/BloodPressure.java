package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class BloodPressure extends Measurement {

    // Constructor
    public BloodPressure(Integer ID, Integer systolic, Integer diastolic, String measuredOn) {
        this.ID = ID;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = 0;
        this.temperature = 0;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    public BloodPressure(Integer systolic, Integer diastolic, String measuredOn) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = 0;
        this.temperature = 0;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Integer getID() { return this.ID; }

    public Integer getSystolic() {
        return this.systolic;
    }

    public Integer getDiastolic() {
        return this.diastolic;
    }

    public String getMeasuredOn() {
        return this.measuredOn;
    }

    public String toString() {
        return this.systolic + " | " +
                this.diastolic + " | " +
                this.measuredOn;
    }

    public BloodPressure getBloodPressure(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setBloodPressure(Integer systolic, Integer diastolic, String measuredOn, Integer ID) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.measuredOn = measuredOn;
        this.ID = ID;
    }
}
