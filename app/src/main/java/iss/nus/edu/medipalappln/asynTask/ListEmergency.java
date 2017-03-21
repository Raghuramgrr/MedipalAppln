package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import iss.nus.edu.medipalappln.dao.EmergencyDataBaseAdapter;

/**
 * Created by root on 21/3/17.
 */

public class ListEmergency extends AsyncTask<Void, Void, String> {

    private static final String TAG = "ListMeasurement";

    private EmergencyDataBaseAdapter emergencyDataBaseAdapter;
    String result;

    public ListEmergency(Context context) {
        this.emergencyDataBaseAdapter = new EmergencyDataBaseAdapter(context);
    }

    @Override
    protected String doInBackground(Void... params) {
       // result = emergencyDataBaseAdapter.databaseToString();
        Log.i(TAG, "List of measurement record(s): " + result);

        return result;
    }


}
