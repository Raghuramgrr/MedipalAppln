package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.adapter.MeasurementListAdapter;
import iss.nus.edu.medipalappln.dao.MeasurementDataBaseAdapter;

public class MeasurementFragment extends Fragment {

    private static final String TAG = "MeasurementFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private TextView measurementSummary;
    //private EditText editTextSystolic;
    //private EditText editTextDiastolic;
    //private EditText editTextMeasuredOn;

    private MeasurementListAdapter measurementListAdapter;
    private MeasurementDataBaseAdapter measurementDataBaseAdapter;
    private TextView textViewEmpty;

    private OnFragmentInteractionListener mListener;

    public MeasurementFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MeasurementFragment newInstance(String param1, String param2) {
        MeasurementFragment fragment = new MeasurementFragment();
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
        View view = inflater.inflate(R.layout.fragment_measurement, container, false);

        textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);

        //measurementSummary = (TextView) view.findViewById(R.id.measurement_summary);
        //editTextSystolic = (EditText) view.findViewById(R.id.edit_text_systolic);
        //editTextDiastolic = (EditText) view.findViewById(R.id.edit_text_diastolic);
        //editTextMeasuredOn = (EditText) view.findViewById(R.id.edit_text_measuredOn);

        //measurementSummary.setText("Summary of measurements");
        ListView listViewMeasurement = (ListView) view.findViewById(R.id.list_view_measurement);
        measurementListAdapter = new MeasurementListAdapter(getActivity(),
                R.layout.measurement_row_layout, R.id.text_view_empty);
        listViewMeasurement.setAdapter(measurementListAdapter);
        //onClickView(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (MeasurementFragment.OnFragmentInteractionListener) context;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onClickAdd(View view) {

    }

    public void onClickDelete(View view) {

    }

    public void onClickView(View view) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //BloodPressure bloodPressure = new BloodPressure(100, 80, "2017-01-02 17:00:00");

        //listMeasurement = new ListMeasurement(this.getContext());

        //listMeasurement.execute((Void) null);

        measurementDataBaseAdapter = new MeasurementDataBaseAdapter(getContext());
        String result = measurementDataBaseAdapter.databaseToString();
        Log.i(TAG, "List of measurement record(s): " + result);

        //measurementSummary.setText(result);
    }

    @Override
    public void onResume() {
        super.onResume();
        measurementListAdapter.refreshList();
        textViewEmpty.setVisibility(measurementListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
