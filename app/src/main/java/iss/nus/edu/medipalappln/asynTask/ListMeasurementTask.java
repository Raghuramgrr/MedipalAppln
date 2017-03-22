package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import iss.nus.edu.medipalappln.dao.MeasurementDataBaseAdapter;
import iss.nus.edu.medipalappln.medipal.Measurement;

public class ListMeasurementTask extends AsyncTask<Void, Void, ArrayList<Measurement>> {

    private static final String TAG = "ListMeasurementTask";

    private MeasurementDataBaseAdapter measurementDataBaseAdapter;

    public ListMeasurementTask(Context context) {
        this.measurementDataBaseAdapter = new MeasurementDataBaseAdapter(context);
    }

    @Override
    protected ArrayList<Measurement> doInBackground(Void... params) {
        ArrayList<Measurement> list = measurementDataBaseAdapter.getMeasurement();

        return list;
    }


}
