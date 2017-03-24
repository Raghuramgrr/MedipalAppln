package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.graphics.Color;
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
    private LineGraphSeries<DataPoint> series1, series2;
    private Button btnShowAll;

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
        btnShowAll = (Button) view.findViewById(R.id.btn_show_all);
        //final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        buildGraphBP(view);
        buildGraphPulse(view);
        buildGraphTemperature(view);
        buildGraphWeight(view);

        btnShowAll.setOnClickListener(new View.OnClickListener() {
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

    public void buildGraphBP(View view) {
        double y1, y2, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_bp);
        series1 = new LineGraphSeries<DataPoint>();
        series2 = new LineGraphSeries<DataPoint>();
        series1.setColor(Color.GREEN);
        series1.setColor(Color.CYAN);

        bloodPressures.clear();
        bloodPressures.addAll(App.user.getBloodPressure(getContext()));
        size = bloodPressures.size();

        if(size <= 0) {
            for (int i = 0; i < size; i++) {
                x = i;
                y1 = bloodPressures.get(i).getSystolic();
                y2 = bloodPressures.get(i).getDiastolic();
                series1.appendData(new DataPoint(x, y1), true, size);
                series2.appendData(new DataPoint(x, y2), true, size);
            }

            graphView.setTitle("Systolic/Diastolic (mmHg)");
            graphView.setBackgroundColor(Color.LTGRAY);
            graphView.addSeries(series1);
            graphView.addSeries(series2);
        }
    }

    public void buildGraphPulse(View view) {
        double y=0, x=0;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_pulse);
        series1 = new LineGraphSeries<DataPoint>();
        series1.setColor(Color.GREEN);

        pulses.clear();
        pulses.addAll(App.user.getPulse(getContext()));
        size = pulses.size();

        if(size <= 0) {
            for (int i = 0; i < size; i++) {
                x = i;
                y = pulses.get(i).getPulse();
                series1.appendData(new DataPoint(x, y), true, size);
            }
            graphView.setTitle("Pulse (bpm)");
            graphView.setBackgroundColor(Color.LTGRAY);
            graphView.addSeries(series1);
            series1.appendData(new DataPoint(x, y), true, size);
        }
    }

    public void buildGraphTemperature(View view) {
        double y, x;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_temperature);
        series1 = new LineGraphSeries<DataPoint>();
        series1.setColor(Color.GREEN);

        temperatures.clear();
        temperatures.addAll(App.user.getTemperature(getContext()));
        size = temperatures.size();

        if(size <= 0) {
            for (int i = 0; i < size; i++) {
                x = i;
                y = temperatures.get(i).getTemperature();
                series1.appendData(new DataPoint(x, y), true, size);
            }
            graphView.setTitle("Temperature (Celcius)");
            graphView.setBackgroundColor(Color.LTGRAY);
            graphView.addSeries(series1);
        }
    }

    public void buildGraphWeight(View view) {
        double y=0, x=0;
        int size = 0;

        GraphView graphView = (GraphView) view.findViewById(R.id.graph_weight);
        series1 = new LineGraphSeries<DataPoint>();
        series1.setColor(Color.GREEN);

        weights.clear();
        weights.addAll(App.user.getWeight(getContext()));
        size = weights.size();

        if(size <= 0) {
            for (int i = 0; i < size; i++) {
                x = i;
                y = weights.get(i).getWeight();
            /*
            String j = "113029";
            Date date = null;

            Log.i(TAG, "Grego date: " + weights.get(i).getMeasuredOn());
            try {
                //date = new SimpleDateFormat("Myydd").parse(j);
                date = new SimpleDateFormat("yyyy-MM-dd").parse(weights.get(i).getWeight().toString());
            } catch (ParseException e) {
                Log.i(TAG, "Unable to convert date");
                e.printStackTrace();
            }
            //String g = new SimpleDateFormat("dd-MM-yyyy").format(date);
            String g = new SimpleDateFormat("Myydd").format(date);
            System.out.println(g);
            Log.i(TAG, "Julian date: " + weights.get(i).getWeight().toString() + "/" + g);
            */
                series1.appendData(new DataPoint(x, y), true, size);

            }
            graphView.setTitle("Weight (kg)");
            graphView.setBackgroundColor(Color.LTGRAY);
            graphView.addSeries(series1);
            series1.appendData(new DataPoint(x, y), true, size);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
