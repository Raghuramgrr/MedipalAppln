package iss.nus.edu.medipalappln.medipal;

/**
 * Created by root on 23/3/17.
 */

public class Personal {
    private int ID;
    private String Name;
    private String Dob;
    private String Idno;
    private String Address;
    private String Postcode;
    private String Height;
    private String Bloodtype;

    public Personal(int ID, String name, String dob, String idno, String address, String postcode, String height, String bloodtype) {
        this.ID = ID;
        Name = name;
        Dob = dob;
        Idno = idno;
        Address = address;
        Postcode = postcode;
        Height = height;
        Bloodtype = bloodtype;
    }


    public String toString() {
        return this.Name + " | " +
                this.Dob+ "|"+
                this.Idno + "|"+
                this.Address + "|"+
                this.Postcode + "|"+
                this.Height + "|"+
                this.Bloodtype;
    }
    public Personal getPersonal(){
        Personal formData=new Personal(ID,Name,Dob,Idno,Address,Postcode,Height,Bloodtype);
        return formData;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getIdno() {
        return Idno;
    }

    public void setIdno(String idno) {
        Idno = idno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPostcode() {
        return Postcode;
    }

    public void setPostcode(String postcode) {
        Postcode = postcode;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getBloodtype() {
        return Bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        Bloodtype = bloodtype;
    }
}
