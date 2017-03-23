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
import iss.nus.edu.medipalappln.adapter.BloodPressureListAdapter;

public class ViewBloodPressureFragment extends Fragment {

    private static final String TAG = "ViewBloodPressureFragment";

    private BloodPressureListAdapter bloodPressureListAdapter;
    private TextView textViewEmpty, textViewHeader;

    private OnFragmentInteractionListener mListener;

    public ViewBloodPressureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_measurement_list, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view_measurement);
        textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);
        textViewHeader = (TextView) view.findViewById(R.id.text_header);

        textViewHeader.setText("systolic/diastolic in mmHg");
        bloodPressureListAdapter = new BloodPressureListAdapter(getActivity(),
                R.layout.measurement_row_layout, R.id.text_view_empty);
        listView.setAdapter(bloodPressureListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (ViewBloodPressureFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onResume() {
        super.onResume();
        bloodPressureListAdapter.refreshList();
        textViewEmpty.setVisibility(bloodPressureListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
