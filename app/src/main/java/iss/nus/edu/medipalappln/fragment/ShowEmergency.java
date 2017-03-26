package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.adapter.EmergencyListAdapter;
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Emergency;

public class ShowEmergency extends Fragment implements View.OnClickListener{

    private List<Emergency> emergencies = new ArrayList<>();
    private static final String TAG = "MeasureBloodPressureFragment";
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textViewEmpty;
    private EmergencyListAdapter emergenyListAdapter;
    private OnFragmentInteractionListener mListener;
    private TextView tvPhone;
    private TextView tvPhone2;private TextView tvPhone3;
    public ShowEmergency() {
        // Required empty public constructor
    }
    public static ShowEmergency newInstance(String param1, String param2) {
        ShowEmergency fragment = new ShowEmergency();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


      View view = inflater.inflate(R.layout.emergencylist, container, false);
      /*  ListView listView= (ListView) view.findViewById(R.id.list_view_measurement);
        textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);
        emergenyListAdapter = new EmergencyListAdapter(getActivity(), R.layout.measurement_row_layout, R.id.text_view_empty);
        listView.setAdapter(emergenyListAdapter);
        */

        // listView.setAdapter(emergenyListAdapter);
        Emergency e1 = App.user.getEmergency("1",getContext());
        Emergency e2 = App.user.getEmergency("2",getContext());
        Emergency e3 = App.user.getEmergency("3",getContext());
        TextView tvName = (TextView) view.findViewById(R.id.text_view_name);
        tvPhone = (TextView) view.findViewById(R.id.phonenum);
        TextView tvRelation = (TextView) view.findViewById(R.id.relation);
        Button call = (Button) view.findViewById(R.id.callButton);
        call.setOnClickListener(this);
        TextView tvName2 = (TextView) view.findViewById(R.id.text_view_name2);
        tvPhone2 = (TextView) view.findViewById(R.id.phonenum2);
        TextView tvRelation2 = (TextView) view.findViewById(R.id.relation2);
        Button call2 = (Button) view.findViewById(R.id.callButton2);
        call.setOnClickListener(this);
        TextView tvName3 = (TextView) view.findViewById(R.id.text_view_name3);
        tvPhone3 = (TextView) view.findViewById(R.id.phonenum3);
        TextView tvRelation3 = (TextView) view.findViewById(R.id.relation3);
        Button call3 = (Button) view.findViewById(R.id.callButton3);
        call.setOnClickListener(this);
        //viewHolder.btnRemove = (Button) convertView.findViewById(R.id.btn_remove);
        tvName.setText(e1.getName());
        tvPhone.setText(e1.getPhone());
        tvRelation.setText(e1.getDesc());
        tvName2.setText(e2.getName());
        tvPhone2.setText(e2.getPhone());
        tvRelation2.setText(e2.getDesc());
        tvName3.setText(e3.getName());
        tvPhone3.setText(e3.getPhone());
        tvRelation3.setText(e3.getDesc());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (ShowEmergency.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.callButton:
                call();
        }


    }

    private void call() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+tvPhone.getText().toString().trim()));
        startActivity(callIntent);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @Override
    public void onResume() {
        super.onResume();
       // emergenyListAdapter.refreshList();
        //textViewEmpty.setVisibility(emergenyListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
