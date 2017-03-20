package iss.nus.edu.medipalappln.dao;

import android.content.ContentValues;
import android.content.Context;

import iss.nus.edu.medipalappln.medipal.Measurement;

public class MeasurementDataBaseAdapter extends DBDAO {

    MeasurementDataBaseAdapter(Context context) {
        super(context);
    }

    public long save(Measurement measurement) {
        ContentValues values = new ContentValues();

        //values.put(DataBaseHelper.COLUMN_MEASUREMENTS[0], record.getId());
        //values.put(DataBaseHelper.COLUMN_MEASUREMENTS[1], record.getName());

        return database.insert(DataBaseHelper.TABLE_MEASUREMENTS, null, values);
    }
}
