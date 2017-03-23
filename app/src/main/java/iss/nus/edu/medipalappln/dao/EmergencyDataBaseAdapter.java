

package iss.nus.edu.medipalappln.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.util.ArrayList;

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
        values.put(DataBaseHelper.ICE.ID.toString(),phone.getID());
        values.put(DataBaseHelper.ICE.Name.toString(),phone.getName());
        values.put(DataBaseHelper.ICE.Sequence.toString(),phone.getPriority());
        values.put(DataBaseHelper.ICE.ContactNo.toString(),phone.getPhone());
        values.put(DataBaseHelper.ICE.ContactType.toString(), phone.getDesc());

        return database.insert(DataBaseHelper.TABLE_ICE, null, values);
    }



public long updateValues(Emergency phone,String key) {
        ContentValues values = new ContentValues();
    String[] args = new String[] {key};

    values.put(DataBaseHelper.ICE.ID.toString(),phone.getID());
    values.put(DataBaseHelper.ICE.Name.toString(),phone.getName());
    values.put(DataBaseHelper.ICE.Sequence.toString(),phone.getPriority());
    values.put(DataBaseHelper.ICE.ContactNo.toString(),phone.getPhone());
    values.put(DataBaseHelper.ICE.ContactType.toString(), phone.getDesc());

    return database.update(DataBaseHelper.TABLE_ICE, values, "ID = ?", args);
    }

    public long deleteValues(String key) {
        String[] args = new String[] {key};

        return database.delete(DataBaseHelper.TABLE_ICE, "ID = ?", args);
    }

public ArrayList<Emergency> getICE() {
    ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
    String query[] = { DataBaseHelper.ICE.ID.toString(),
            DataBaseHelper.ICE.Name.toString(),
            DataBaseHelper.ICE.ContactNo.toString(),
            DataBaseHelper.ICE.ContactType.toString(),
            DataBaseHelper.ICE.Sequence.toString()
             };
    int id;
try {
    Cursor cursor = database.query(DataBaseHelper.TABLE_ICE, query, null, null, null,
            null, null);

    while (cursor.moveToNext()) {
        id = cursor.getInt(0);
        Emergency emergency = new Emergency(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));
        emergencies.add(emergency);
    }
}
catch (SQLException e){
    e.printStackTrace();
}
    return emergencies;


}



    public Emergency getEmergency(int id) {
        Emergency emergency = null;

        String sql = "SELECT * FROM " + DataBaseHelper.TABLE_ICE
                + " WHERE " + id + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
           int eid = cursor.getInt(0);
            String priority = cursor.getString(3);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String desc=cursor.getString(4);

            emergency = new Emergency(eid, name, phone, priority,desc);
        }
        return emergency;
    }





    public String getSingleEntry() {
        String dbString = "";

        Cursor cursor = database.rawQuery(SELECT_ALL, null);
        cursor.moveToFirst();

        Log.i(TAG, "Record(s) count " + cursor.getCount());

        for( int i = 0; i < cursor.getCount(); i++) {
            if(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.ID.toString())) != null) {

                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.Name.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.ContactNo.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.ContactType.toString()));
                dbString += "\t";
                dbString += cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.Sequence.toString()));
                dbString += "\t";

                Log.i(TAG, "Record: " + dbString);
            }
            cursor.moveToNext();
        }

        return dbString;
    }
}





/*

package iss.nus.edu.medipalappln.dao;

*/
/**
 * Created by root on 15/3/17.
 *//*



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

*/
/* public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }*//*
*/
/*

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
       *//*

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
/*


    *//*

*/
/*String selectTicketDetails = "SELECT ID,SUBJECT,DETAILS,PRIORITY FROM TICKET_DETAILS where SUBJECT=\""
            + subject + "\"";

    Cursor mCursor = db.rawQuery(selectTicketDetails, null);

		if (mCursor != null) {
    mCursor.moveToFirst();
}
		return mCursor;*//*
*/
/*


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