package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.adapter.MeasurementListAdapter;

public class MeasureBloodPressureFragment extends Fragment {

    private static final String TAG = "MeasureBloodPressureFragment";

    private MeasurementListAdapter measurementListAdapter;
    private TextView textViewEmpty;

    private OnFragmentInteractionListener mListener;

    public MeasureBloodPressureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blood_pressure, container, false);
        ListView listViewMeasurement = (ListView) view.findViewById(R.id.list_view_measurement);
        textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);

        measurementListAdapter = new MeasurementListAdapter(getActivity(),
                R.layout.measurement_row_layout, R.id.text_view_empty);
        listViewMeasurement.setAdapter(measurementListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (MeasureBloodPressureFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onResume() {
        super.onResume();
        measurementListAdapter.refreshList();
        textViewEmpty.setVisibility(measurementListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
