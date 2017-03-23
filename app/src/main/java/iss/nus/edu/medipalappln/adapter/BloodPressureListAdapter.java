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
import iss.nus.edu.medipalappln.medipal.BloodPressure;

public class BloodPressureListAdapter extends ArrayAdapter {

    private static final String TAG = "BPListAdapter";

    private Context context;
    private List<BloodPressure> bloodPressures = new ArrayList<BloodPressure>();

    public BloodPressureListAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
        refreshList();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        Log.i(TAG, "Retrieving record " + position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.measurement_row_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewSystolic = (TextView) convertView.findViewById(R.id.text_view_col1);
            viewHolder.textViewDiastolic = (TextView) convertView.findViewById(R.id.text_view_col2);
            viewHolder.textViewMeasuredOn = (TextView) convertView.findViewById(R.id.text_view_measured_on);
            viewHolder.buttonDelete = (Button) convertView.findViewById(R.id.button_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (bloodPressures.size() != 0) {
            final BloodPressure bloodPressure = bloodPressures.get(position);

            viewHolder.textViewSystolic.setText(bloodPressure.getSystolic().toString());
            viewHolder.textViewDiastolic.setText(bloodPressure.getDiastolic().toString());
            viewHolder.textViewMeasuredOn.setText(bloodPressure.getMeasuredOn());

            viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    App.user.deleteBloodPressure(context, bloodPressure.getID());
                    refreshList();
                    Log.i(TAG, "Deleted record: " + bloodPressure.getID());
                }
            });

            /*
            viewHolder.buttonUpdate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    App.user.updateBloodPressure(context, bloodPressure.getID());
                    refreshList();
                    Log.i(TAG, "Updated record: " + bloodPressure.getID());
                }
            });
            */
        }
        else
        {
            Log.i(TAG, "No record found");
        }

        return convertView;
    }

    public void refreshList() {
        if (App.user == null) {
            Log.e(TAG, "Critical: App.user object is null");
        }
        else {
            bloodPressures.clear();
            bloodPressures.addAll(App.user.getBloodPressure(context));
            Log.i(TAG, "refreshList");
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return bloodPressures.size();
    }

    static class ViewHolder {
        TextView textViewSystolic;
        TextView textViewDiastolic;
        TextView textViewMeasuredOn;
        Button buttonDelete;
        Button buttonUpdate;
    }
}
