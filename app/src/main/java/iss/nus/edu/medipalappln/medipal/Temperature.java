package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Temperature extends Measurement {

    // Constructor
    public Temperature(Integer temperature, Date measuredOn) {
        this.temperature = temperature;
        this.measuredOn = measuredOn;
    }

    // Methods
    public BloodPressure getTemperature(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setTemperature(Integer temperature, Date measuredOn, Integer ID) {
        this.temperature = temperature;
        this.measuredOn = measuredOn;
    }
}
