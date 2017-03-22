package iss.nus.edu.medipalappln.medipal;

public class Measurement {
    String decimalPattern = "#####.##";

    protected Integer ID;
    protected Integer systolic;
    protected Integer diastolic;
    protected Integer pulse;
    protected Integer temperature;
    protected Double weight;
    protected String measuredOn;

    // Constructor

    public Measurement() {
    }

    public Measurement(Integer systolic, Integer diastolic, Integer pulse, Integer temperature,
                       Double weight, String measuredOn) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.temperature = temperature;
        this.weight = weight;
        this.measuredOn = measuredOn;
    }

    // Methods
    public Measurement getMeasurement() {
        Measurement measurement = new Measurement(systolic, diastolic, pulse,
                                    temperature, weight, measuredOn);
        return measurement;
    }

    public String toString() {
        return this.systolic + " | " +
                this.diastolic + " | " +
                this.pulse + this.temperature + " | " +
                this.weight + this.measuredOn;
    }

}