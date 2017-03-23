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
import iss.nus.edu.medipalappln.medipal.Pulse;

public class PulseListAdapter extends ArrayAdapter {

    private static final String TAG = "PulseListAdapter";

    private Context context;
    private List<Pulse> pulses = new ArrayList<Pulse>();

    public PulseListAdapter(Context context, int resource, int textViewResourceId) {
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
            viewHolder.textViewPulse = (TextView) convertView.findViewById(R.id.text_view_col1);
            viewHolder.textViewMeasuredOn = (TextView) convertView.findViewById(R.id.text_view_measured_on);
            viewHolder.buttonDelete = (Button) convertView.findViewById(R.id.button_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (pulses.size() != 0) {
            final Pulse pulse = pulses.get(position);

            viewHolder.textViewPulse.setText(pulse.getPulse().toString());
            viewHolder.textViewMeasuredOn.setText(pulse.getMeasuredOn());

            viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    App.user.deletePulse(context, pulse.getID());
                    refreshList();
                    Log.i(TAG, "Deleted record: " + pulse.getID());
                }
            });
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
            pulses.clear();
            pulses.addAll(App.user.getPulse(context));
            Log.i(TAG, "refreshList");
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return pulses.size();
    }

    static class ViewHolder {
        TextView textViewPulse;
        TextView textViewMeasuredOn;
        Button buttonDelete;
    }
}