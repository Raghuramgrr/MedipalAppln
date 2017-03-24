package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Pulse extends Measurement {

    // Constructor
    public Pulse(Integer ID, Integer pulse, String measuredOn) {
        this.ID = ID;
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = pulse;
        this.temperature = 0;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    public Pulse (Integer pulse, String measuredOn) {
        this.systolic = 0;
        this.diastolic = 0;
        this.pulse = pulse;
        this.temperature = 0;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Integer getID() { return this.ID; }

    public Integer getPulse() { return this.pulse; }

    public String getMeasuredOn() {
        return this.measuredOn;
    }

    public String toString() {
        return this.pulse + " | " +
                this.measuredOn;
    }

    public Pulse getPulse(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setPulse(Integer pulse, String measuredOn, Integer ID) {
        this.pulse = pulse;
        this.measuredOn = measuredOn;
        this.ID = ID;
    }

}
