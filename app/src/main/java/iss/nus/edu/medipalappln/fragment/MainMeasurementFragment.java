package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import iss.nus.edu.medipalappln.R;

public class MainMeasurementFragment extends Fragment {

    private static final String TAG = "MainMeasurementFragment";

    //TODO: populate with measurement records
    private LineGraphSeries<DataPoint> series;
    private Button btn_show_all;

    private OnFragmentInteractionListener mListener;

    public MainMeasurementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_measurement_graph, container, false);
        btn_show_all = (Button) view.findViewById(R.id.btn_show_all);
        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        double y, x;
        x = 5.0;

        GraphView graphBP = (GraphView) view.findViewById(R.id.graph_bp);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 500; i++) {
            x = x + 0.1;
            y = 5.0 * x;
            series.appendData(new DataPoint(x, y), true, 500);
        }
        graphBP.addSeries(series);

        btn_show_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to show all measurement fragment");
                ShowAllMeasurementFragment fragment = new ShowAllMeasurementFragment()    ;

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
            mListener = (MainMeasurementFragment.OnFragmentInteractionListener) context;
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

}
