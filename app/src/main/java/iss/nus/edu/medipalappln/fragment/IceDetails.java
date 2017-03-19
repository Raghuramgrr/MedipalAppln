package iss.nus.edu.medipalappln.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.Session;
import iss.nus.edu.medipalappln.dao.EmergencyDataBaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IceDetails.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IceDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IceDetails extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EmergencyDataBaseAdapter emergencyDataBaseAdapter;
    private Session session;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public IceDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IceDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static IceDetails newInstance(String param1, String param2) {
        IceDetails fragment = new IceDetails();
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

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.icedetails,container,false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emergencyDataBaseAdapter=new EmergencyDataBaseAdapter(this.getActivity());
        //emergencyDataBaseAdapter.close();
        emergencyDataBaseAdapter.open();
        session=new Session(this.getActivity());


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button btnAddEmergency = (Button) view.findViewById(R.id.add1);
        btnAddEmergency.setOnClickListener(this);
        final Button btnAddEmergency2=(Button)view.findViewById(R.id.add2);
        btnAddEmergency2.setOnClickListener(this);
        final Button btnAddEmergency3=(Button)view.findViewById(R.id.add3);
        btnAddEmergency3.setOnClickListener(this);
        final Button showEmergency1=(Button)view.findViewById(R.id.emergency1);
        showEmergency1.setOnClickListener(this);


    }
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.add1:
                insertEntry(v,"1");
                break;
            case R.id.add2:
                insertEntry(v, "2");
                break;
            case R.id.add3:
                insertEntry(v, "3");
                break;
            case R.id.emergency1:
                selectEntry(v,"1");
                break;


        }
    }



private void selectEntry(View v, String s) {
    emergencyDataBaseAdapter.getSingleEntry(session.username(),"1");
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
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void insertEntry(View v, String prio) {
        if(prio=="1") {
            final EditText editTextPhone = (EditText) getView().findViewById(R.id.emergencyPhone1);
            String phone1 = editTextPhone.getText().toString();
            emergencyDataBaseAdapter.insertEntry(session.username(), phone1, prio);
            Toast.makeText(getView().getContext() , "Updated succesfully", Toast.LENGTH_SHORT).show();
        }
        else if(prio=="2"){
            final EditText editTextPhone = (EditText) getView().findViewById(R.id.emergencyPhone2);
            String phone1 = editTextPhone.getText().toString();
            emergencyDataBaseAdapter.insertEntry(session.username(), phone1, prio);
            Toast.makeText(getActivity().getApplicationContext(), "Updated succesfully", Toast.LENGTH_SHORT).show();
        }
        else if(prio=="3"){
            final EditText editTextPhone = (EditText) getView().findViewById(R.id.emergencyPhone3);
            String phone1 = editTextPhone.getText().toString();
            emergencyDataBaseAdapter.insertEntry(session.username(), phone1, prio);
            Toast.makeText(getActivity().getApplicationContext(), "Updated succesfully", Toast.LENGTH_SHORT).show();

        }



    }
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        emergencyDataBaseAdapter.close();
    }

}