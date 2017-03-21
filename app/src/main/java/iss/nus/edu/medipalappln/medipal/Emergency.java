package iss.nus.edu.medipalappln.medipal;

/**
 * Created by root on 21/3/17.
 */

public class Emergency extends Measurement {
    private String phone;
    private String priority;

    public Emergency(String phone, String priority) {
        this.phone=phone;
        this.priority=priority;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhoneNumber(String phone, String priority) {
        this.phone=phone;
        this.priority = priority;
        }

    public String getPriority() {
        return priority;
    }
}
