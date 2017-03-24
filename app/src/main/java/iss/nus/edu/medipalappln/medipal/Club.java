//package iss.nus.edu.medipalappln.medipal;
//
///**
// * Created by swarna on 4/8/2016.
// */
//
//import android.content.Context;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//import iss.nus.edu.medipalappln.asynTask.*;
//
//
//public class Club {
//
//    private int numMembers;
//    private ArrayList<Medicine> medicines;
//    private ArrayList<Category> facilities;
//    private Category category;
//    private Reminder reminder;
//
//    private HashMap<String, Category> facilities1;
//    private ArrayList<Consumption> consumptions;
//  //  private BookingRegister           bookings1;
//
//    private AddMedicine taskMemberAdd;
//    private ListMedicine taskMemberList;
//    private AddCategory taskFacilityAdd;
//    private ListCategory taskFacilityList;
//    private AddConsumption taskBookingAdd;
//    private ListConsumption taskBookingList;
//
//    private UpdateCategory taskFacilityUpdate;
//    private DeleteMedicine taskDeleteMedicine;
//
//    private UpdateMedicine taskUpdateMedicine;
//    private GetCategory taskGetCategory;
//
//    private AddReminder taskAddReminder;
//    private GetReminder taskGetReminder;
//
//    private GetMaxReminderId taskGetMaxReminderId;
//
//    public Club () {
//        numMembers = 0;
//        medicines = new ArrayList<Medicine>();
//        facilities = new ArrayList<Category>();
//        //facilities = new HashMap<String, Category> ();
//        consumptions = new ArrayList<Consumption>();
//        //bookings1 = new BookingRegister ();
//    }
//
//    public void updateFacility(int id, String name, String code, String description, String reminder, Context context){
//        Category f = new Category(id, name, code, description, reminder);
//        taskFacilityUpdate = new UpdateCategory(context);
//        taskFacilityUpdate.execute(f);
//
//    }
//
//    public void updateMember(Medicine medicine, Context context){
//        taskUpdateMedicine = new UpdateMedicine(context);
//        taskUpdateMedicine.execute(medicine);
//
//    }
//
//    public Category getFacility (int facilityNum, Context context) {
//        taskGetCategory = new GetCategory(context);
//        taskGetCategory.execute(facilityNum);
//        try {
//            category = taskGetCategory.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (category == null) { category = new Category(); }
//        // SQLite - End
//
//        return category;
//    }
//
//    public Medicine getMember (int memberNum) {
//        Iterator<Medicine> i = medicines.iterator ();
//        while (i.hasNext ()) {
//            Medicine m = i.next();
//            if (m.getMedId() == memberNum) {
//                return m;
//            }
//        }
//        return null;
//    }
//
//    public List<Medicine> getMedicines (Context context) {
//        // SQLite - Start
//        taskMemberList = new ListMedicine(context);
//        taskMemberList.execute((Void) null);
//        try {
//            medicines = taskMemberList.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (medicines == null) { medicines = new ArrayList<Medicine>(); }
//        // SQLite - End
//
//        return medicines;
//    }
//
//    public Medicine addMember(String medName, String medDesc, int catId, int remindID,
//                              int quantity, int dosage, int threshold, Date dateIssued, int expiryFactor) {
//        numMembers++;
//        Log.d("Niv", "inserting medicine");
//        Medicine m = new Medicine(medName, medDesc, catId, remindID, quantity, dosage, threshold, dateIssued, expiryFactor);
//        medicines.add (m);
//        return m;
//    }
//
//    // SQLite
//    public Medicine addMember (String medName, String medDesc, int catId, int remindID,
//                               int quantity, int dosage, int threshold, Date dateIssued, int expiryFactor, Context context) {
//        Medicine m = new Medicine(medName, medDesc, catId, remindID, quantity, dosage, threshold, dateIssued, expiryFactor);
//
//        taskMemberAdd = new AddMedicine(context);
//        taskMemberAdd.execute(m);
//        return m;
//    }
//
//    public void removeMember (Medicine m, Context context) {
///*        Medicine m = getMedicine (memberNum);
//        if (m != null) {
//            medicines.remove (m);
//        }*/
//        taskDeleteMedicine = new DeleteMedicine(context);
//        taskDeleteMedicine.execute(m);
//
//    }
//
//    public void showMembers () {
//        Iterator<Medicine> i = medicines.iterator ();
//        while (i.hasNext ()) {
//            i.next();
//        }
//    }
//
//    /*
//    public Category getCategory (String name) {
//
//        return facilities.get (name);
//    }*/
//
//
//
//    public List<Category> getFacilities (Context context) {
//        // SQLite - Start
//        taskFacilityList = new ListCategory(context);
//        taskFacilityList.execute((Void) null);
//        try {
//            facilities = taskFacilityList.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (facilities == null) { facilities = new ArrayList<Category>(); }
//        // SQLite - End
//        return (new ArrayList<Category>(facilities));
//    }
//
//    public void addFacility (String name, String description, String code, String reminder) {
//        if (name == null) {
//            return;
//        }
//        Category f = new Category(name, description, code, reminder);
//        facilities1.put (name, f);
//    }
//
//    // SQLite
//    public Category addFacility (String name, String description, String code, String reminder, Context context) {
//        if (name == null) {
//            return null;
//        }
//        Category f = new Category(name, description, code, reminder);
//
//
//        taskFacilityAdd = new AddCategory(context);
//        taskFacilityAdd.execute(f);
//        return f;
//    }
//
//    public void removeFacility (String name) {
//
//        facilities.remove (name);
//    }
//
//    /*
//    public void showFacilities () {
//        Iterator<Category> i = getFacilities().iterator ();
//        while (i.hasNext ()) {
//            i.next().show ();
//        }
//    }
//    */
//
//    public Reminder addReminder(String rfreq, String rStTime, String rInterval, Context context){
//        Reminder r = new Reminder(rfreq, rStTime, rInterval);
//        taskAddReminder = new AddReminder(context);
//        taskAddReminder.execute(r);
//        Log.d("Niv_1", taskAddReminder.toString());
//        return r;
//    }
//
//    public Reminder getReminder(Context context){
//        taskGetReminder = new GetReminder(context);
//        taskGetReminder.execute((Void) null);
//
//        try {
//            reminder = taskGetReminder.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (reminder == null) { reminder = new Reminder(); }
//        // SQLite - End
//
//        return (reminder);
//    }
//
//    public Consumption addConsumption (int memberNumber, int facilityNumber, Date startDate, Context context)
//    {
//        //bookings1.addBooking (getMedicine (memberNumber), getCategory (facName), startDate, endDate);
//
//        Consumption b = new Consumption(memberNumber, facilityNumber, startDate);
//        taskBookingAdd = new AddConsumption(context);
//        taskBookingAdd.execute(b);
//        return b;
//    }
//
//
//    public void removeConsumption (Consumption consumption) {
//
//        consumptions.remove(consumption);
//    }
//
//    public ArrayList<Consumption> getConsumptions (Context context) {
//        //return bookings.getBookings (getCategory (facName), startDate, endDate);
//
//        // SQLite - Start
//        taskBookingList = new ListConsumption(context);
//        taskBookingList.execute((Void) null);
//        try {
//            consumptions = taskBookingList.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (consumptions == null) { consumptions = new ArrayList<Consumption>(); }
//        // SQLite - End
//        return (new ArrayList<Consumption>(consumptions));
//    }
//
//    /*
//    public void showBookings (String facName, Date startDate, Date endDate) {
//        ArrayList<Booking> b = getBookings (facName, startDate, endDate);
//        Iterator<Booking> i = b.iterator();
//        while (i.hasNext()) {
//            i.next().show();
//        }
//    }
//    */
//
//
//    public void show () {
//        System.out.println ("Current Members:");
//        showMembers ();
//        System.out.println ();
//        System.out.println ("Facilities:");
//        //showFacilities ();
//    }
//
//    public int getMaxReminderId(Context context) {
//        int maxRemindId = -1;
//        taskGetMaxReminderId = new GetMaxReminderId(context);
//        taskGetMaxReminderId.execute((Void) null);
//        try {
//            maxRemindId = taskGetMaxReminderId.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return maxRemindId;
//    }
//}
