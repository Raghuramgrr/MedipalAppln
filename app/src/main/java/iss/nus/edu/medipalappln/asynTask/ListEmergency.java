package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import iss.nus.edu.medipalappln.dao.EmergencyDataBaseAdapter;
import iss.nus.edu.medipalappln.medipal.Emergency;

/**
 * Created by root on 22/3/17.
 */

public class ListEmergency extends AsyncTask <Void, Void, ArrayList<Emergency>>  {
    ArrayList<Emergency> emergencies;
    private EmergencyDataBaseAdapter emergencyDataBaseAdapter;

    public ListEmergency(Context context){
        emergencyDataBaseAdapter=new EmergencyDataBaseAdapter(context);

    }

    @Override
    protected ArrayList<Emergency> doInBackground(Void... params) {
        ArrayList<Emergency> list=emergencyDataBaseAdapter.getICE();
        return list;

    }
}
