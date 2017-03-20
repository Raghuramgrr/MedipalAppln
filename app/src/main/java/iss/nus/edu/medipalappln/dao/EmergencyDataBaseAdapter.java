package iss.nus.edu.medipalappln.dao;

/**
 * Created by root on 15/3/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmergencyDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 2;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE_Emergency = "create table "+"EMERGENCYBIO"+
            "( " + "EMAIL text,PHONENUMBER integer,PRIORITY text); ";
    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  EmergencyDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  EmergencyDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String email, String Phonenumb, String priority)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("EMAIL", email);
        newValues.put("PHONENUMBER",Phonenumb);
        newValues.put("PRIORITY",priority);

        try {
            db.insert("EMERGENCYBIO", null, newValues);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        }

   /* public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }*/
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
       /* Cursor cursor=db.query("EMERGENCYBIO", null, " PRIORITY=?", new String[]{priority}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        //String phone= cursor.getString(cursor.getColumnIndex("PHONENUMBER"));
        cursor.close();*/

    /*String selectTicketDetails = "SELECT ID,SUBJECT,DETAILS,PRIORITY FROM TICKET_DETAILS where SUBJECT=\""
            + subject + "\"";

    Cursor mCursor = db.rawQuery(selectTicketDetails, null);

		if (mCursor != null) {
    mCursor.moveToFirst();
}
		return mCursor;*/

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
