package iss.nus.edu.medipalappln.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Measurement;

public class MeasurementListAdapter extends ArrayAdapter {

    private static final String TAG = "MeasurementListAdapter";

    private Context context;
    private List<Measurement> measurements = new ArrayList<>();

    public MeasurementListAdapter(Context context) {
        super(context, R.layout.measurement_row_layout);
        this.context = context;
        refreshList();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.measurement_row_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewMeasurement = (TextView) convertView.findViewById(R.id.text_view_measurement);
            viewHolder.buttonDelete = (Button) convertView.findViewById(R.id.button_delete);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Measurement measurement = measurements.get(position);
        Log.i(TAG, position + " - measurement: " + measurement.toString());
        //TODO: add delete button listener

        //return super.getView(position, convertView, parent);
        return convertView;
    }

    public void refreshList() {
        if (App.user == null) {
            Log.i(TAG, "App.user is null");
        }
        else {
            measurements.clear();
            measurements.addAll(App.user.getMeasurements(context));
            notifyDataSetChanged();
        }
    }

    static class ViewHolder {
        TextView textViewMeasurement;
        Button buttonDelete;
    }
}
