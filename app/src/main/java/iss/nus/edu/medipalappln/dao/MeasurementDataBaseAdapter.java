package iss.nus.edu.medipalappln.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import iss.nus.edu.medipalappln.medipal.BloodPressure;
import iss.nus.edu.medipalappln.medipal.Measurement;

public class MeasurementDataBaseAdapter extends DBDAO {

    private static final String TAG = "DBDAO";

    //begin SQL statements
    public static final String SELECT_ALL = "SELECT * FROM " + DataBaseHelper.TABLE_MEASUREMENT +
            " WHERE 1";
    //end SQL statements

    public MeasurementDataBaseAdapter(Context context) {
        super(context);
    }

    public long addBloodPressure(BloodPressure bloodPressure) {
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

    public ArrayList<Measurement> getMeasurement() {
        ArrayList<Measurement> measurements = new ArrayList<Measurement>();
        String query[] = { DataBaseHelper.MEASUREMENT.ID.toString(),
                        DataBaseHelper.MEASUREMENT.Systolic.toString(),
                        DataBaseHelper.MEASUREMENT.Diastolic.toString(),
                        DataBaseHelper.MEASUREMENT.Temperature.toString(),
                        DataBaseHelper.MEASUREMENT.Pulse.toString(),
                        DataBaseHelper.MEASUREMENT.Weight.toString() };
        int id;

        Cursor cursor = database.query(DataBaseHelper.TABLE_MEASUREMENT, query, null, null, null,
                            null, null);

        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
            Measurement measurement = new Measurement(cursor.getInt(0), cursor.getInt(1),
                    cursor.getInt(2), cursor.getInt(3), cursor.getDouble(4), cursor.getString(5));
            measurements.add(measurement);
        }

        return measurements;
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
