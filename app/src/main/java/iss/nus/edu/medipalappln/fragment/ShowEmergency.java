package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.adapter.EmergencyListAdapter;
import iss.nus.edu.medipalappln.medipal.App;

public class ShowEmergency extends Fragment {

    private static final String TAG = "MeasureBloodPressureFragment";
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView textViewEmpty;
    private EmergencyListAdapter emergenyListAdapter;
    private OnFragmentInteractionListener mListener;

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

        App.user.getEmergency("1",getContext());

      View view = inflater.inflate(R.layout.emergencylist, container, false);
      /*  ListView listView= (ListView) view.findViewById(R.id.list_view_measurement);
        textViewEmpty = (TextView) view.findViewById(R.id.text_view_empty);
        emergenyListAdapter = new EmergencyListAdapter(getActivity(), R.layout.measurement_row_layout, R.id.text_view_empty);
        listView.setAdapter(emergenyListAdapter);
*/

       // listView.setAdapter(emergenyListAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
