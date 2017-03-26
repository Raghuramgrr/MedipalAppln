package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageButton;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.HealthBioCatalogActivity;
import iss.nus.edu.medipalappln.activity.Session;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private ImageButton ibtnMeasurement, ibtnAppointment,
            ibtnMedicine, ibtnHealthBio, ibtnPersonalBio;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //check if user has registered
        Session session = new Session(getContext());
        Log.i(TAG, "Session: " + session.username().toString());
        //App.user.getUserIDNo();

        ibtnPersonalBio = (ImageButton) view.findViewById(R.id.ibtn_personal_bio);
        ibtnPersonalBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to personal bio fragment");
                PersonalBioForm fragment = new PersonalBioForm();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ibtnMeasurement = (ImageButton) view.findViewById(R.id.ibtn_measurement);
        ibtnMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to main measurement fragment");
                MainMeasurementFragment fragment = new MainMeasurementFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ibtnMedicine = (ImageButton) view.findViewById(R.id.ibtn_medicine);
        ibtnMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to main medicine fragment");
                MainMedicineFragment fragment = new MainMedicineFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        ibtnAppointment = (ImageButton) view.findViewById(R.id.ibtn_appt);
        ibtnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to main appointment fragment");
                AppointmentFragment fragment = new AppointmentFragment();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        ibtnHealthBio = (ImageButton) view.findViewById(R.id.ibtn_health_bio);
        ibtnHealthBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Switch to main health bio fragment");
                Intent intent = new Intent(getActivity(), HealthBioCatalogActivity.class);
                startActivity(intent);
            }
        });

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
            mListener = (MainFragment.OnFragmentInteractionListener) context;
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

    public void onClickMeasurement() {

    }
}
