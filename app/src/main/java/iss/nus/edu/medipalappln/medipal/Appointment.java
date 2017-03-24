package iss.nus.edu.medipalappln.medipal;

/**
 * Created by cherry on 2017/3/22.
 */

public class Appointment {

    protected int id;
    protected String location;
    protected String date;
    protected String description;


    public Appointment(String location, String date, String description){
        this.location = location;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
