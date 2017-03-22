package iss.nus.edu.medipalappln.medipal;

import java.sql.Date;

public class Pulse extends Measurement {

    // Constructor
    public Pulse(Integer pulse, String measuredOn) {
        this.pulse = pulse;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Pulse getPulse(Date startDate, Date endDate) {
        //search within timeframe
        return null;
    }

    public void setPulse(Integer pulse, String measuredOn, Integer ID) {
        this.pulse = pulse;
        this.measuredOn = measuredOn;
    }

}
