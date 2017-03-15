package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Weight extends Measurement {

    // Constructor
    public Weight(Double weight, Date measuredOn) {
        //this.weight.applyPattern(decimalPattern);
        this.weight = weight;
        this.measuredOn = measuredOn;
    }

    // Methods
    public BloodPressure getWeight(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setWeight(Double weight, Date measuredOn, Integer ID) {
        this.weight = weight;
        this.measuredOn = measuredOn;
    }
}
