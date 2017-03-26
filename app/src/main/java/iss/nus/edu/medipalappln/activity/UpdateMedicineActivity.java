package iss.nus.edu.medipalappln.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Category;
import iss.nus.edu.medipalappln.medipal.Medicine;
import iss.nus.edu.medipalappln.medipal.Reminder;

/**
 * Created by niv on 3/21/2017.
 */

public class UpdateMedicineActivity extends AppCompatActivity{

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private static final SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy H:mm", Locale.ENGLISH);
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();


    EditText editName,editDescription,editCategory,editRemind,editQuantity,editDosage,editThreshold,editDateIssued,
            editExpiryFactor,etReminderFreq,etReminderInterval,txtStTime;
    Button updateBtn;
    int quantity, dosage, threshold, expiryFactor;
    int catId, remID;
    Context context;
    List<Category> categoryList = null;
    Spinner spnFacility;
    Switch reminderToggle;
    String remindFlag = "FALSE";
    LinearLayout remindLayout,remindContentLayout;
    Reminder r1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        getLayoutInflater().inflate(R.layout.fragment_medicine, null);

        reminderToggle = (Switch) findViewById(R.id.rem_upd_switch1);

        remindLayout = (LinearLayout) findViewById(R.id.rem_switch_layout2);
        remindContentLayout = (LinearLayout) findViewById(R.id.reminder_content_update_layout);

        remindLayout.setVisibility(View.GONE);
        remindContentLayout.setVisibility(View.GONE);

        spnFacility = (Spinner) findViewById(R.id.spn_facility_1);

        editName = (EditText) findViewById(R.id.et_medName);
        editDescription = (EditText) findViewById(R.id.et_medDesc);

        editQuantity = (EditText) findViewById(R.id.et_quantity_upd);
        editDosage = (EditText) findViewById(R.id.et_medDosage_upd);
        editThreshold = (EditText) findViewById(R.id.et_medThreshold_upd);

        editExpiryFactor = (EditText) findViewById(R.id.et_medExpiryFactor_upd);

        etReminderFreq = (EditText) findViewById(R.id.et_remind_freq_update);
        etReminderInterval = (EditText) findViewById(R.id.et_remindInterval_update);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(currentCal.getTime());
        timeCalendar.set(Calendar.HOUR, currentCal.get(Calendar.HOUR));
        timeCalendar.set(Calendar.MINUTE, currentCal.get(Calendar.MINUTE));
        timeCalendar.set(Calendar.AM_PM, currentCal.get(Calendar.AM_PM));
        timeCalendar.add(Calendar.HOUR, 1);
        txtStTime = (EditText)findViewById(R.id.select_start_time_update);

        View.OnClickListener timeClickListener = new View.OnClickListener() {
            @Override public void onClick(final View v) {
                final EditText editText = (EditText) v;
                TimePickerDialog.OnTimeSetListener timeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute);
                                editText.setText(timeFormatter.format(calendar.getTime()));
                            }
                        };
                Calendar timeCalendar = Calendar.getInstance();
                try {
                    timeCalendar.setTime(timeFormatter.parse(editText.getText().toString()));
                } catch (ParseException e) {
                    Toast.makeText(UpdateMedicineActivity.this, R.string.generic_error, Toast.LENGTH_SHORT)
                            .show();
                }
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(UpdateMedicineActivity.this, timeSetListener,
                                timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        };

        txtStTime.setOnClickListener(timeClickListener);

        editDateIssued = (EditText) findViewById(R.id.et_medDateIssued_upd);

        Intent intent = getIntent();
        final int medId = intent.getExtras().getInt("medId");
        Log.d("Update",String.valueOf(medId));

        final Medicine m1 = App.user.getMember(medId,context);

        if(m1.getRemindId()!=0){
            r1 = App.user.getReminder(m1.getRemindId(),context);
            reminderToggle.setChecked(true);

            remindLayout.setVisibility(View.VISIBLE);
            remindContentLayout.setVisibility(View.VISIBLE);

            etReminderFreq.setText(r1.getFrequency());
            etReminderInterval.setText(r1.getInterval());
            txtStTime.setText(timeFormatter.format(r1.getStartTime()).toString());
            Log.d("Update",txtStTime.getText().toString());
        }else{
            reminderToggle.setVisibility(View.GONE);
            remindLayout.setVisibility(View.GONE);
            remindContentLayout.setVisibility(View.GONE);
        }

        editDateIssued.setText(dateFormatter.format(m1.getDateIssued().getTime()));

        editDateIssued.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                DatePickerDialog.OnDateSetListener onDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                selectedDate = calendar;
                                editDateIssued.setText(dateFormatter.format(calendar.getTime()));
                            }
                        };
                DatePickerDialog datePickerDialog =
                        new DatePickerDialog(UpdateMedicineActivity.this, onDateSetListener,
                                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                                currentCal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        editName.setText(m1.getMedName());
        editDescription.setText(m1.getMedDesc());
        Log.d("Update",m1.getMedName());
        Log.d("Update", String.valueOf(m1.getDosage()));

        try {
            editQuantity.setText(""+m1.getQuantity());
            editDosage.setText(""+m1.getDosage());
            editThreshold.setText(""+m1.getThreshold());
            editExpiryFactor.setText(""+m1.getExpireFactor());
            Log.d("Update", String.valueOf(m1.getExpireFactor()));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        Category c1 = App.user.getFacility(m1.getCatId(),context);
        catId = c1.getCatId();
        categoryList = App.user.getFacilities(context);
        categoryList.remove(c1);

        final List<String> spnFacList = new ArrayList<>();

        spnFacList.add(c1.getName());
        for (Category category : categoryList) {
            spnFacList.add(category.getName());
        }
        final ArrayAdapter<String> spnFacAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnFacList);
        spnFacility.setAdapter(spnFacAdapter);

        updateBtn = (Button) findViewById(R.id.btn_med_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    quantity = Integer.parseInt(editQuantity.getText().toString());
                    dosage = Integer.parseInt(editDosage.getText().toString());
                    threshold = Integer.parseInt(editThreshold.getText().toString());
                    expiryFactor = Integer.parseInt(editExpiryFactor.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }


                Calendar selectedStTime = Calendar.getInstance();
                try {
                    selectedStTime.setTime(dateFormatter.parse(editDateIssued.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if(!reminderToggle.isChecked()) {
                    Medicine m = new Medicine(medId, editName.getText().toString(), editDescription.getText().toString(),
                            catId, 0, "FALSE",
                            quantity, dosage, threshold,
                            selectedStTime.getTime(), expiryFactor);

                    App.user.updateMember(m, getApplicationContext());
                    finish();
                }else{
                    etReminderFreq = (EditText) findViewById(R.id.et_remind_freq);
                    etReminderInterval = (EditText) findViewById(R.id.et_remindInterval);


                    Calendar selectedRemindTime = Calendar.getInstance();
                    try {
                        selectedRemindTime.setTime(timeFormatter.parse(txtStTime.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }

                    int remId = App.user.addReminder(etReminderFreq.getText().toString(),selectedRemindTime.getTime(),
                            etReminderInterval.getText().toString(),context);
                    Medicine m = new Medicine(medId, editName.getText().toString(), editDescription.getText().toString(),
                            catId, remId,"TRUE",
                            quantity, dosage, threshold,
                            selectedStTime.getTime(), expiryFactor);
                    App.user.updateMember(m, getApplicationContext());
                    finish();
                }
            }
        });
    }

}
