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
import iss.nus.edu.medipalappln.medipal.Emergency;

public class EmergencyListAdapter extends ArrayAdapter<Emergency> {
    private static final String  TAG = "EMERGENCYLISTADAPTER";

    private Context context;
    private List<Emergency> emergencies = new ArrayList<>();

    public EmergencyListAdapter(Context context,int resource, int textViewResourceId) {
        super(context, R.layout.mem_fac_row_layout);
        this.context = context;
       // refreshMembejava.lang.Stringrs();
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mem_fac_row_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.btnRemove = (Button) convertView.findViewById(R.id.btn_remove);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Emergency emergency = emergencies.get(position);
        viewHolder.tvName.setText(emergency.toString());
        viewHolder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
               // App.user.removeMember(emergency.getMemberNumber());
                refreshList();
            }
        });
        return convertView;
    }
    public void refreshList() {
        if (App.user == null) {
            Log.i(TAG, "App.user is null");
        }
        else {
            emergencies.clear();
            //mergencies.addAll(App.user.getEmergency(context));
            Log.i(TAG, "refreshList");
            notifyDataSetChanged();
        }
    }

    @Override public int getCount() {
        return emergencies.size();
    }

    static class ViewHolder {
        TextView tvName;
        Button btnRemove;
    }
}