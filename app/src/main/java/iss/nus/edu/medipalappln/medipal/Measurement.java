package iss.nus.edu.medipalappln.medipal;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Measurement {
    String decimalPattern = "#####.##";

    private Integer ID;
    private Integer systolic;
    private Integer diastolic;
    private Integer pulse;
    private Integer temperature;
    private DecimalFormat weight;
    private SimpleDateFormat measuredOn;

    private Integer Type;

    // Constructor
    public Measurement(Integer type, SimpleDateFormat date) {

        switch(type) {

            case 1: {
                this.systolic = systolic;
                this.diastolic = diastolic;
                this.measuredOn = date;
            }
            case 2: {
                this.pulse = pulse;
                this.measuredOn = date;
            }
            case 3: {
                this.temperature = temperature;
                this.measuredOn = date;
            }
            case 4: {
                this.weight = new DecimalFormat(decimalPattern);
                //this.weight = weight.format(weight);
                this.measuredOn = date;
            }
        }
    }

    // Methods

}
