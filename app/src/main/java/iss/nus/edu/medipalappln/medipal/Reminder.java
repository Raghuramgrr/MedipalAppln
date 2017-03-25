package iss.nus.edu.medipalappln.medipal;

/**
 * Created by rama on 3/22/2017.
 */

public class Reminder {

    private int remindId;
    private String frequency;
    private String startTime;
    private String interval;

    public Reminder() {
    }

    public Reminder(int remindId, String frequency, String startTime, String interval) {
        this.remindId = remindId;
        this.frequency = frequency;
        this.startTime = startTime;
        this.interval = interval;
    }

    public Reminder(String frequency, String startTime, String interval) {
        this.frequency = frequency;
        this.startTime = startTime;
        this.interval = interval;
    }

    public int getRemindId() {
        return remindId;
    }

    public void setRemindId(int remindId) {
        this.remindId = remindId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
