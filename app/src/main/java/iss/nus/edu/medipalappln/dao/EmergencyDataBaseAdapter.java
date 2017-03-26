

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

public ArrayList<Emergency> get() {
    ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
    String query[] = { DataBaseHelper.ICE.ID.toString(),
            DataBaseHelper.ICE.Name.toString(),
            DataBaseHelper.ICE.ContactNo.toString(),
            DataBaseHelper.ICE.ContactType.toString(),
            DataBaseHelper.ICE.Sequence.toString()
             };
    int id;
    String where = DataBaseHelper.ICE.ContactNo + " >0 ";
try {
    Cursor cursor = database.query(DataBaseHelper.TABLE_ICE, query, where, null, null,
            null, null);

    while (cursor.moveToNext()) {
        id = cursor.getInt(0);
        //cursor.getInt(cursor.getColumnIndex(DataBaseHelper.MEASUREMENT.Systolic.toString())),
        Emergency emergency = new Emergency(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ICE.ID.toString())),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.Name.toString())),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.ContactNo.toString())),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.Sequence.toString())),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ICE.ContactType.toString())));
        emergencies.add(emergency);
    }
    //int ID,String name ,String phone, String priority,String desc
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




