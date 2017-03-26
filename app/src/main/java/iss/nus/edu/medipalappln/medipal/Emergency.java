package iss.nus.edu.medipalappln.medipal;

/**
 * Created by root on 21/3/17.
 */

public class Emergency {
    private String phone;
    private String priority;
    private String desc;
    private int ID;
    private String name;

    public Emergency(int ID,String name ,String phone, String priority,String desc) {
        this.ID=ID;
        this.phone=phone;
        this.priority=priority;
        this.desc=desc;
        this.name=name;
    }

    public String getPhone() {
        return phone;
    }

    public Emergency getEmergency() {
        Emergency emergency = new Emergency(ID,priority,phone,name,desc);
        return emergency;
    }
    public void setPhoneNumber(int ID,String name ,String phone, String priority,String desc) {
        this.ID=ID;
        this.name=name;
        this.desc=desc;
        this.phone=phone;
        this.priority = priority;
        }

    public String getPriority() {
        return this.priority;
    }

    public String toString() {
        return this.phone + " | " +
                this.desc + "|"+
                this.name + "|"+
                this.priority;
    }

    public Integer getID() {
        return this.ID;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }
}
