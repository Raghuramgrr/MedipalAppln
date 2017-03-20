package iss.nus.edu.medipalappln.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;

import iss.nus.edu.medipalappln.medipal.BloodPressure;

public class MeasurementDataBaseAdapter extends DBDAO {

    private static final String TAG = "DBDAO";

    //begin SQL statements
    public static final String SELECT_ALL = "SELECT * FROM " + DataBaseHelper.TABLE_MEASUREMENT +
            " WHERE 1";
    //end SQL statements

    MeasurementDataBaseAdapter(Context context) {
        super(context);
    }

    public long saveBloodPressure(BloodPressure bloodPressure) {
        ContentValues values = new ContentValues();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(bloodPressure.getMeasuredOn());

        values.put(DataBaseHelper.MEASUREMENT.Systolic.toString(), bloodPressure.getSystolic());
        values.put(DataBaseHelper.MEASUREMENT.Diastolic.toString(), bloodPressure.getDiastolic());
        values.put(DataBaseHelper.MEASUREMENT.MeasuredOn.toString(), date);

        return database.insert(DataBaseHelper.TABLE_MEASUREMENT, null, values);
    }

    public long updateBloodPressure(BloodPressure bloodPressure, String key) {
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

    public String databaseToString() {
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
