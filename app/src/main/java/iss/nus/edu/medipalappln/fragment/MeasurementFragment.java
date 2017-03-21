package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.adapter.MeasurementPagerAdapter;

public class MeasurementFragment extends Fragment implements
        MeasureBloodPressureFragment.OnFragmentInteractionListener {

    private static final String TAG = "MeasurementFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private MeasurementListAdapter measurementListAdapter;
    //private MeasurementDataBaseAdapter measurementDataBaseAdapter;
    //private TextView textViewEmpty;

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
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        MeasurementPagerAdapter measurementPagerAdapter = new MeasurementPagerAdapter(fragmentManager, 4);
        viewPager.setAdapter(measurementPagerAdapter);

        final TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Blood Pressure").setIcon(R.drawable.ic_measurement));
        tabLayout.addTab(tabLayout.newTab().setText("Pulse").setIcon(R.drawable.ic_measurement));
        tabLayout.addTab(tabLayout.newTab().setText("Temperature").setIcon(R.drawable.ic_measurement));
        tabLayout.addTab(tabLayout.newTab().setText("Weight").setIcon(R.drawable.ic_measurement));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Fragment childFragment = viewPager.
        //FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        //transaction.replace(R.id.child_fragment_container, childFragment.commit);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
