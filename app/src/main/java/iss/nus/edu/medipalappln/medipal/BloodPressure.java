package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class BloodPressure extends Measurement {

    // Constructor
    public BloodPressure(Integer systolic, Integer diastolic, Date measuredOn) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = 0;
        this.temperature = 0;
        this.weight = 0.0;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Integer getSystolic() {
        return 0;
    }

    public Integer getDiastolic() {
        return 0;
    }

    public Date getMeasuredOn() {
        return null;
    }

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
