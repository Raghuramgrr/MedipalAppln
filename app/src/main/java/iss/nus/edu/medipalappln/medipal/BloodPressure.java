package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class BloodPressure extends Measurement {

    // Constructor
    public BloodPressure(Integer systolic, Integer diastolic, Date measuredOn) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.measuredOn = measuredOn;
    }

    // Methods
    public BloodPressure getBloodPressure(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setBloodPressure(Integer systolic, Integer diastolic, Date measuredOn, Integer ID) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.measuredOn = measuredOn;
    }
}
