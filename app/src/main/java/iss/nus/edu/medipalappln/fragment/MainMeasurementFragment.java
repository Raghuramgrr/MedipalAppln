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

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.BloodPressure;
import iss.nus.edu.medipalappln.medipal.Pulse;
import iss.nus.edu.medipalappln.medipal.Temperature;
import iss.nus.edu.medipalappln.medipal.Weight;

public class MainMeasurementFragment extends Fragment {

    private static final String TAG = "MainMeasurementFragment";

    private LineGraphSeries<DataPoint> series;
    private Button btn_show_all;
    private List<BloodPressure> bloodPressures = new ArrayList<BloodPressure>();
    private List<Pulse> pulses = new ArrayList<Pulse>();
    private List<Temperature> temperatures = new ArrayList<Temperature>();
    private List<Weight> weights = new ArrayList<Weight>();

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
        //final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        buildGraphBP(view);
        buildGraphPulse(view);
        buildGraphTemperature(view);
        buildGraphWeight(view);

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

    public void buildGraphBP(View view) {
        double y, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_bp);
        series = new LineGraphSeries<DataPoint>();

        bloodPressures.clear();
        bloodPressures.addAll(App.user.getBloodPressure(getContext()));
        size = bloodPressures.size();

        for (int i = 0; i < size; i++) {
            x = i;
            y = bloodPressures.get(i).getSystolic();
            series.appendData(new DataPoint(x, y), true, size);
        }
        graphView.addSeries(series);
    }

    public void buildGraphPulse(View view) {
        double y, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_pulse);
        series = new LineGraphSeries<DataPoint>();

        pulses.clear();
        pulses.addAll(App.user.getPulse(getContext()));
        size = pulses.size();

        for (int i = 0; i < size; i++) {
            x = i;
            y = pulses.get(i).getPulse();
            series.appendData(new DataPoint(x, y), true, size);
        }
        graphView.addSeries(series);
    }

    public void buildGraphTemperature(View view) {
        double y, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_temperature);
        series = new LineGraphSeries<DataPoint>();

        temperatures.clear();
        temperatures.addAll(App.user.getTemperature(getContext()));
        size = temperatures.size();

        for (int i = 0; i < size; i++) {
            x = i;
            y = temperatures.get(i).getTemperature();
            series.appendData(new DataPoint(x, y), true, size);
        }
        graphView.addSeries(series);
    }

    public void buildGraphWeight(View view) {
        double y, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_weight);
        series = new LineGraphSeries<DataPoint>();

        weights.clear();
        weights.addAll(App.user.getWeight(getContext()));
        size = weights.size();

        for (int i = 0; i < size; i++) {
            x = i;
            y = weights.get(i).getWeight();
            series.appendData(new DataPoint(x, y), true, size);
        }
        graphView.addSeries(series);
    }

}
