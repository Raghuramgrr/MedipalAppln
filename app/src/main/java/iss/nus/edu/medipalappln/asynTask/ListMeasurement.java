package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import iss.nus.edu.medipalappln.dao.MeasurementDataBaseAdapter;

public class ListMeasurement extends AsyncTask<Void, Void, String> {

    private static final String TAG = "ListMeasurement";

    private MeasurementDataBaseAdapter measurementDataBaseAdapter;
    String result;

    public ListMeasurement(Context context) {
        this.measurementDataBaseAdapter = new MeasurementDataBaseAdapter(context);
    }

    @Override
    protected String doInBackground(Void... params) {
        result = measurementDataBaseAdapter.databaseToString();
        Log.i(TAG, "List of measurement record(s): " + result);

        return result;
    }


}
