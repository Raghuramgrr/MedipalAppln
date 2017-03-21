
/*
package iss.nus.edu.medipalappln.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import iss.nus.edu.medipalappln.medipal.Emergency;

public class EmergencyDataBaseAdapter extends DBDAO {

    private static final String TAG = "DBDAO";

    //begin SQL statements
    public static final String SELECT_ALL = "SELECT * FROM " + DataBaseHelper.TABLE_ICE +
            " WHERE 1";
    //end SQL statements

    public EmergencyDataBaseAdapter(Context context) {
        super(context);
    }

    public long addValues(Emergency phone) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.MEASUREMENT.Systolic.toString(), phone.getPhone());
        values.put(DataBaseHelper.MEASUREMENT.Diastolic.toString(), phone.getPriority());

        return database.insert(DataBaseHelper.TABLE_ICE, null, values);
    }


*/
/*public long updateBloodPressure(BloodPressure bloodPressure, String key) {
        ContentValues values = new ContentValues();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(bloodPressure.getMeasuredOn());
        String[] args = new String[] {key};

        values.put(DataBaseHelper.MEASUREMENT.Systolic.toString(), bloodPressure.getSystolic());
        values.put(DataBaseHelper.MEASUREMENT.Diastolic.toString(), bloodPressure.getDiastolic());
        values.put(DataBaseHelper.MEASUREMENT.MeasuredOn.toString(), date);

        return database.update(DataBaseHelper.TABLE_MEASUREMENT, values, "ID = ?", args);
    }

    public long deleteBloodPressure(String key) {
        String[] args = new String[] {key};

        return database.delete(DataBaseHelper.TABLE_MEASUREMENT, "ID = ?", args);
    }
*//*
*/
/**//*
*/
/**//*


    public String getSingleEntry() {
        String dbString = "";

        Cursor cursor = database.rawQuery(SELECT_ALL, null);
        cursor.moveToFirst();

        Log.i(TAG, "Record(s) count " + cursor.getCount());

        for( int i = 0; i < cursor.getCount(); i++) {
            if(cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.ID.toString())) != null) {
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Systolic.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Diastolic.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Pulse.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Temperature.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Weight.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.MeasuredOn.toString()));
                dbString += "\n";
                Log.i(TAG, "Record: " + dbString);
            }
            cursor.moveToNext();
        }

        return dbString;
    }
}


*/



package iss.nus.edu.medipalappln.dao;

/**
 * Created by root on 15/3/17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmergencyDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 2;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE_Emergency = "create table " + "EMERGENCYBIO" +
            "( " + "EMAIL text,PHONENUMBER integer,PRIORITY text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper2 dbHelper;

    public EmergencyDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper2(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public EmergencyDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String email, String Phonenumb, String priority) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("EMAIL", email);
        newValues.put("PHONENUMBER", Phonenumb);
        newValues.put("PRIORITY", priority);

        try {
            db.insert("EMERGENCYBIO", null, newValues);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/* public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }*//*

    public String getSingleEntry(String userName,String priority)
    {
        String phone = "";
        try {

            String selectQuery = "SELECT * FROM EMERGENCYBIO where EMAIL=\"" + userName + "\"";
            Cursor mCursor = db.rawQuery(selectQuery, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
                phone = mCursor.getString(mCursor.getColumnIndex("PHONENUMBER"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return phone;
    }
       */
/* Cursor cursor=db.query("EMERGENCYBIO", null, " PRIORITY=?", new String[]{priority}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        //String phone= cursor.getString(cursor.getColumnIndex("PHONENUMBER"));
        cursor.close();*//*


    */
/*String selectTicketDetails = "SELECT ID,SUBJECT,DETAILS,PRIORITY FROM TICKET_DETAILS where SUBJECT=\""
            + subject + "\"";

    Cursor mCursor = db.rawQuery(selectTicketDetails, null);

		if (mCursor != null) {
    mCursor.moveToFirst();
}
		return mCursor;*//*


   public void  updateEntry(String userName,String PhoneNumber,String priority)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("EMAIL", userName);
        updatedValues.put("PHONENUMBER",PhoneNumber);
        updatedValues.put("PRIORITY",priority);

        String where="USERNAME = ?";
        db.update("EmergencyBio",updatedValues, where, new String[]{userName});
    }
}
*/
