package iss.nus.edu.medipalappln.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;

public class AddMeasurementFragment extends Fragment {

    private DatePicker datePicker;
    private Calendar calendar;
    private CheckBox checkBoxBP, checkBoxPulse, checkBoxWeight, checkBoxTemperature;
    private TextView dateView;
    private EditText editTextSystolic, editTextDiastolic,
            editTextPulse,
            editTextWeight,
            editTextTemperature;
    private Button btn_calendar, btn_add, btn_delete;
    private int year, month, day;
    boolean isBp = false, isPulse = false, isWeight = false, isTemperature = false;
    private OnFragmentInteractionListener mListener;

    public AddMeasurementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_measurement, container, false);
        editTextSystolic = (EditText) view.findViewById(R.id.edit_text_systolic);
        editTextDiastolic = (EditText) view.findViewById(R.id.edit_text_diastolic);
        editTextPulse = (EditText) view.findViewById(R.id.edit_text_pulse);
        editTextWeight = (EditText) view.findViewById(R.id.edit_text_weight);
        editTextTemperature = (EditText) view.findViewById(R.id.edit_text_temperature);
        dateView = (EditText) view.findViewById(R.id.text_view_measured_on);
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        checkBoxBP = (CheckBox) view.findViewById(R.id.chkbox_option_bp);
        checkBoxPulse = (CheckBox) view.findViewById(R.id.chkbox_option_pulse);
        checkBoxWeight = (CheckBox) view.findViewById(R.id.chkbox_option_weight);
        checkBoxTemperature = (CheckBox) view.findViewById(R.id.chkbox_option_temp);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dateView.setText(year + "-" + month + "-" + day);

        checkBoxBP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editTextSystolic.setVisibility(View.VISIBLE);
                    editTextDiastolic.setVisibility(View.VISIBLE);
                    isBp = true;
                }
                else {
                    editTextSystolic.setVisibility(View.GONE);
                    editTextDiastolic.setVisibility(View.GONE);
                    isBp = false;
                }
            }
        });

        checkBoxPulse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editTextPulse.setVisibility(View.VISIBLE);
                    isPulse = true;
                }
                else {
                    editTextPulse.setVisibility(View.GONE);
                    isPulse = false;
                }
            }
        });

        checkBoxTemperature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editTextTemperature.setVisibility(View.VISIBLE);
                    isTemperature = true;
                }
                else {
                    editTextTemperature.setVisibility(View.GONE);
                    isTemperature = false;
                }
            }
        });

        checkBoxWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    editTextWeight.setVisibility(View.VISIBLE);
                    isWeight = true;
                }
                else {
                    editTextWeight.setVisibility(View.GONE);
                    isWeight = false;
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBp && isValidBP() ) {
                    Integer systolic = Integer.parseInt(editTextSystolic.getText().toString());
                    Integer diastolic = Integer.parseInt(editTextDiastolic.getText().toString());
                    App.user.addBloodPressure(getContext(), systolic, diastolic, dateView.getText().toString().trim());
                    Toast.makeText(getContext(), getString(R.string.save_success),
                            Toast.LENGTH_SHORT).show();

                }

                if (isPulse && isValidPulse() ) {
                    Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
                    App.user.addPulse(getContext(), pulse, dateView.getText().toString().trim());
                    Toast.makeText(getContext(), getString(R.string.save_success),
                            Toast.LENGTH_SHORT).show();

                }

                if (isTemperature && isValidTemperature() ) {
                    Integer temp = Integer.parseInt(editTextTemperature.getText().toString());
                    App.user.addTemperature(getContext(), temp, dateView.getText().toString().trim());
                    Toast.makeText(getContext(), getString(R.string.save_success),
                            Toast.LENGTH_SHORT).show();

                }

                if (isWeight && isValidWeight() ) {
                    Double weight = Double.parseDouble(editTextWeight.getText().toString());
                    App.user.addWeight(getContext(), weight, dateView.getText().toString().trim());
                    Toast.makeText(getContext(), getString(R.string.save_success),
                            Toast.LENGTH_SHORT).show();

                }

                //getActivity().finish();
                ShowAllMeasurementFragment fragment = new ShowAllMeasurementFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.flContent, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllMeasurementFragment fragment = new ShowAllMeasurementFragment();
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view, Bundle bundle) {
        //showDialog(1, bundle);
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 1) {
            return new DatePickerDialog(getContext(),
                    dateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(day + "/" + month + "/" + year);
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        showDate(year, month, dayOfMonth);
    }

    private boolean isValidBP() {
        boolean isValid = true;

        if (TextUtils.isEmpty(editTextSystolic.getText().toString().trim())) {
            editTextSystolic.setError(getString(R.string.error_invalid_data));
            isValid = false;
        }
        if (TextUtils.isEmpty(editTextDiastolic.getText().toString().trim())) {
            editTextDiastolic.setError(getString(R.string.error_invalid_data));
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidPulse() {
        boolean isValid = true;

        if (TextUtils.isEmpty(editTextPulse.getText().toString().trim())) {
            editTextPulse.setError(getString(R.string.error_invalid_data));
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidTemperature() {
        boolean isValid = true;
        Integer t;
        t = Integer.parseInt(editTextTemperature.getText().toString().trim());
        if (t < 0) {
            editTextTemperature.setError(getString(R.string.error_invalid_data));
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidWeight() {
        boolean isValid = true;

        if (TextUtils.isEmpty(editTextWeight.getText().toString().trim())) {
            editTextWeight.setError(getString(R.string.error_invalid_data));
            isValid = false;
        }

        return isValid;
    }

}
