package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import iss.nus.edu.medipalappln.dao.MeasurementDataBaseAdapter;
import iss.nus.edu.medipalappln.medipal.Measurement;

public class ListMeasurement extends AsyncTask<Void, Void, ArrayList<Measurement>> {

    private static final String TAG = "ListMeasurement";

    private MeasurementDataBaseAdapter measurementDataBaseAdapter;
    ArrayList<Measurement> measurements;
    //String result;

    public ListMeasurement(Context context) {
        this.measurementDataBaseAdapter = new MeasurementDataBaseAdapter(context);
    }

    @Override
    protected ArrayList<Measurement> doInBackground(Void... params) {
        ArrayList<Measurement> list = measurementDataBaseAdapter.getMeasurement();
        //result = measurementDataBaseAdapter.databaseToString();
        //Log.i(TAG, "List of measurement record(s): " + result);

        return list;
    }


}
