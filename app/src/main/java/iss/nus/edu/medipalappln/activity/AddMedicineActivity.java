package iss.nus.edu.medipalappln.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
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
import iss.nus.edu.medipalappln.medipal.Reminder;

public class AddMedicineActivity extends AppCompatActivity {

  private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
  /*private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
  private SimpleDateFormat combinedFormatter = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault());*/
  Calendar currentCal = Calendar.getInstance();
  Calendar selectedDate = Calendar.getInstance();

  private EditText etMedicineName, etMedicineDesc,etQuantity, etDosage, etThreshold, etDateIssued, etExpireFactor;
  private EditText etReminderFreq, etReminderStTime, etReminderInterval;
  Reminder reminderObj;

  boolean flag = false;

  Switch remindSwitch;
  int quantity, dosage, threshold, expiryFactor;
  List<Category> categoryList = null;
  Spinner spnFacility;
  LinearLayout remindLayout;
  Category selectedCategory;
  String categorySelected;
  String remindFlag;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_medicine);

    remindLayout = (LinearLayout) findViewById(R.id.switch_layout2);
    remindLayout.setVisibility(View.INVISIBLE);

    spnFacility = (Spinner) findViewById(R.id.spn_facility);

    categoryList = App.user.getFacilities(this);
    final List<String> spnFacList = new ArrayList<>();
    spnFacList.add("<Select Category>");
    for (Category category : categoryList) {
      spnFacList.add(category.getName());
    }
    final ArrayAdapter<String> spnFacAdapter =
            new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnFacList);
    spnFacility.setAdapter(spnFacAdapter);
    spnFacility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorySelected = (String) parent.getSelectedItem();
        Log.d("Niv1", categorySelected);

        for(Category c: categoryList){
          if(c.getName()==categorySelected){
            selectedCategory = c;
            if(c.getReminder().equalsIgnoreCase("REMINDER ENABLED")){
              remindLayout.setVisibility(View.VISIBLE);
            }else
              remindLayout.setVisibility(View.INVISIBLE);
          }
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });

    remindSwitch = (Switch) findViewById(R.id.switch1);
    remindSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == true) {
          flag = true;
          remindLayout = (LinearLayout) findViewById(R.id.relative_layout3);
          remindSwitch.setText("Reminder ON", null);
          remindFlag = "TRUE";
          Intent intent = new Intent(getApplicationContext(), AddReminderActivity.class);
          getApplicationContext().startActivity(intent);

        } else{
          flag = false;
          remindSwitch.setText("Reminder OFF", null);
          remindLayout.setVisibility(View.GONE);
          remindFlag = "FALSE";
        }
      }
    });

    etDateIssued = (EditText) findViewById(R.id.et_date_issued);

    etDateIssued.setText(dateFormatter.format(selectedDate.getTime()));
    etDateIssued.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DatePickerDialog.OnDateSetListener onDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    selectedDate = calendar;
                    etDateIssued.setText(dateFormatter.format(calendar.getTime()));
                  }
                };
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(AddMedicineActivity.this, onDateSetListener,
                        currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                        currentCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
      }
    });

    final  int remId = App.user.getMaxReminderId(getApplicationContext());

    Button btnSave = (Button) findViewById(R.id.btn_save);

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        etMedicineName = (EditText) findViewById(R.id.et_medicine_name);
        etMedicineDesc = (EditText) findViewById(R.id.et_medicine_desc);

        Calendar selectedStTime = Calendar.getInstance();
        try {
          selectedStTime.setTime(dateFormatter.parse(etDateIssued.getText().toString()));
        } catch (ParseException e) {
          e.printStackTrace();
        }

        etQuantity = (EditText) findViewById(R.id.et_quantity);
        etDosage = (EditText) findViewById(R.id.et_dosage);
        etThreshold = (EditText) findViewById(R.id.et_threshold);
        etExpireFactor = (EditText) findViewById(R.id.et_expiry_factor);


        try {
          quantity = Integer.parseInt(etQuantity.getText().toString());
          dosage = Integer.parseInt(etDosage.getText().toString());
          threshold = Integer.parseInt(etThreshold.getText().toString());
          expiryFactor = Integer.parseInt(etExpireFactor.getText().toString());
        }catch (NumberFormatException e){
          e.printStackTrace();
        }

        if (flag) {
          App.user.addMember(etMedicineName.getText().toString().trim(), etMedicineDesc.getText().toString().trim(),
                  selectedCategory.getCatId(), (remId+1),remindFlag,
                  quantity,
                  dosage,threshold,
                  selectedStTime.getTime(), expiryFactor, getApplicationContext());
          Toast.makeText(AddMedicineActivity.this, getString(R.string.save_successful),
                  Toast.LENGTH_SHORT).show();
          finish();
        }else{
          App.user.addMember(etMedicineName.getText().toString().trim(), etMedicineDesc.getText().toString().trim(),
                  selectedCategory.getCatId(), 0,remindFlag,
                  quantity,
                  dosage,threshold,
                  selectedStTime.getTime(), expiryFactor, getApplicationContext());
          Toast.makeText(AddMedicineActivity.this, getString(R.string.save_successful),
                  Toast.LENGTH_SHORT).show();
          finish();
        }

      }
    });

  }

  private boolean isValid() {
    boolean isValid = true;
    if (TextUtils.isEmpty(etMedicineName.getText().toString().trim())) {
      etMedicineName.setError(getString(R.string.first_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etMedicineDesc.getText().toString().trim())) {
      etMedicineDesc.setError(getString(R.string.second_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etDosage.getText().toString().trim())) {
      etDosage.setError(getString(R.string.sur_name_validation_msg));
      isValid = false;
    }
    return isValid;
  }
}
