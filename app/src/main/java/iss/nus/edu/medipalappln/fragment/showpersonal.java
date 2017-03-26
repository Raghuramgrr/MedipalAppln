package iss.nus.edu.medipalappln.fragment;
/**
 * Created by Raghu on 7/3/17.
 */
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
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Personal;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link showpersonal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link showpersonal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showpersonal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText name,blood,height,weight,address,postcode;
    private EditText phonenumber,dob,nric;

    private String sblood="";
    private String sheight="";
    private String sname="";
    private String sweight="";
    private String saddress="";
    private String spostcode="";
    private String sphonenumb="";
    private String snric="";
    //private  String sname;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public showpersonal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment showpersonal.
     */
    // TODO: Rename and change types and number of parameters
    public static showpersonal newInstance(String param1, String param2) {
        showpersonal fragment = new showpersonal();
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
        view=inflater.inflate(R.layout.showpersonalbio,container,false);

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
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Personal personal= App.user.getPersonal(getContext());
        name=(EditText) getView().findViewById(R.id.personName);
        blood=(EditText)getView().findViewById(R.id.personblood);
        height=(EditText)getView().findViewById(R.id.personheight);
        weight=(EditText)getView().findViewById(R.id.personweight);
        nric=(EditText)getView().findViewById(R.id.personnric);
        postcode=(EditText)getView().findViewById(R.id.personpostalcode);
        address=(EditText)getView().findViewById(R.id.personaddress);
        phonenumber=(EditText)getView().findViewById(R.id.personphone);
        Button edit=(Button)getView().findViewById(R.id.personaledit);
       /* name.setText("");blood.setText("");height.setText("");weight.setText("");phonenumber.setText("");
        nric.setText("");
       */ try {
            sname=personal.getName().trim();
            sblood=personal.getBloodtype().trim();
            saddress=personal.getAddress().trim();
            sweight=personal.getWeight().trim();
            sphonenumb=personal.getPhone().trim();
            snric=personal.getIdno().trim();
            spostcode=personal.getPostcode().trim();
            sheight=personal.getHeight().trim();
//saddress=pers
            name.setText(sname);
            blood.setText(sblood);
            height.setText(sheight+"in cms");
            weight.setText(sweight+" in kgs");
            phonenumber.setText(""+sphonenumb);
            nric.setText(""+snric);

            postcode.setText(""+spostcode);
            address.setText(""+saddress);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            /*name.setText("");blood.setText("");height.setText("");weight.setText("");phonenumber.setText("");
            nric.setText("");*/
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //App.user.update("Raghu");
                Toast.makeText(getContext(),"Updated details",Toast.LENGTH_LONG).show();

            }
        });
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
