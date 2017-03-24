package iss.nus.edu.medipalappln.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import iss.nus.edu.medipalappln.medipal.Consumption;
import iss.nus.edu.medipalappln.medipal.Medicine;
import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;

public class AddConsumptionActivity extends AppCompatActivity {

  private Spinner spnMember, spnFacility;
  private EditText etDate, etStrtTime, etEndTime, etQuantity;
  int quantity;
  private Button btnSave;
  private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
  private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
  private SimpleDateFormat combinedFormatter = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault());
  Calendar currentCal = Calendar.getInstance();
  Calendar selectedDate = Calendar.getInstance();


  List<Medicine> medicineList = null;
  //List<Facility> facilityList =  null;
//  Member selMember = null;
//  Facility selFacility = null;
//  Date selStartDate = null;
//  Date selEndDate = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_booking);

    spnMember = (Spinner) findViewById(R.id.spn_member);
   // spnFacility = (Spinner) findViewById(R.id.spn_facility);
    etDate = (EditText) findViewById(R.id.et_select_date);
    etStrtTime = (EditText) findViewById(R.id.et_select_strt_time);
    //etEndTime = (EditText) findViewById(R.id.et_select_end_time);
    etQuantity = (EditText) findViewById(R.id.txt_quantity) ;
    btnSave = (Button) findViewById(R.id.btn_save);



    medicineList = App.user.getMedicines(this);
    List<String> spnMemList = new ArrayList<>();
    spnMemList.add("<Select Medicine>");
    for (Medicine medicine : medicineList) {
      spnMemList.add(medicine.getMedId(), medicine.getMedName().toString());
    }
    ArrayAdapter<String> spnMemAdapter =
            new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnMemList);
    spnMember.setAdapter(spnMemAdapter);

//    facilityList = App.club.getFacilities(this);
//    List<String> spnFacList = new ArrayList<>();
//    spnFacList.add("<Select Category>");
//    for (Facility facility : facilityList) {
//      spnFacList.add(facility.getFacilityNumber(), facility.toString());
//    }
//    ArrayAdapter<String> spnFacAdapter =
//        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spnFacList);
//    spnFacility.setAdapter(spnFacAdapter);

    etDate.setText(dateFormatter.format(selectedDate.getTime()));
    etDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialog.OnDateSetListener onDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
              @Override
              public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                selectedDate = calendar;
                etDate.setText(dateFormatter.format(calendar.getTime()));
              }
            };
        DatePickerDialog datePickerDialog =
            new DatePickerDialog(AddConsumptionActivity.this, onDateSetListener,
                currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),
                currentCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
      }
    });

    etStrtTime.setText(timeFormatter.format(currentCal.getTime()));
    Calendar timeCalendar = Calendar.getInstance();
    timeCalendar.setTime(currentCal.getTime());
    timeCalendar.set(Calendar.HOUR, currentCal.get(Calendar.HOUR));
    timeCalendar.set(Calendar.MINUTE, currentCal.get(Calendar.MINUTE));
    timeCalendar.set(Calendar.AM_PM, currentCal.get(Calendar.AM_PM));
    timeCalendar.add(Calendar.HOUR, 1);
    //etEndTime.setText(timeFormatter.format(timeCalendar.getTime()));

    View.OnClickListener timeClickListener = new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        final EditText editText = (EditText) v;
        TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
              @Override
              public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
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
          Toast.makeText(AddConsumptionActivity.this, R.string.generic_error, Toast.LENGTH_SHORT)
              .show();
        }
        TimePickerDialog timePickerDialog =
            new TimePickerDialog(AddConsumptionActivity.this, timeSetListener,
                timeCalendar.get(Calendar.HOUR_OF_DAY), timeCalendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
      }
    };

    etStrtTime.setOnClickListener(timeClickListener);
    //etEndTime.setOnClickListener(timeClickListener);

    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isValid()) {


          Medicine selectedMedicine = null;
          //Facility selectedFacility = null;

          for (Medicine m : medicineList) {
            Long id = spnMember.getSelectedItemId();
            if (id.intValue() == m.getMedId()) {

              selectedMedicine = m;
            }
          }
//


          Calendar selectedStTime = Calendar.getInstance();
          try {
            selectedStTime.setTime(timeFormatter.parse(etStrtTime.getText().toString()));
          } catch (ParseException e) {
            e.printStackTrace();
          }
/*          Calendar convertedStTime = Calendar.getInstance();
          convertedStTime.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
          convertedStTime.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
          convertedStTime.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));*/

//          Calendar selectedEtTime = Calendar.getInstance();
//          try {
//            selectedEtTime.setTime(timeFormatter.parse(etEndTime.getText().toString()));
//          } catch (ParseException e) {
//            e.printStackTrace();
//          }
/*          Calendar convertedEtTime = Calendar.getInstance();
          convertedEtTime.set(Calendar.HOUR, selectedEtTime.get(Calendar.HOUR));
          convertedEtTime.set(Calendar.MINUTE, selectedEtTime.get(Calendar.MINUTE));
          convertedEtTime.set(Calendar.AM_PM, selectedEtTime.get(Calendar.AM_PM));*/

          Calendar selStartDate = Calendar.getInstance();
          selStartDate.set(Calendar.YEAR, selectedDate.get(Calendar.YEAR));
          selStartDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH));
          selStartDate.set(Calendar.DATE, selectedDate.get(Calendar.DATE));
          selStartDate.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
          selStartDate.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
          selStartDate.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));

//          Calendar selEndDate = Calendar.getInstance();
//          selEndDate.set(Calendar.YEAR, selectedDate.get(Calendar.YEAR));
//          selEndDate.set(Calendar.MONTH, selectedDate.get(Calendar.MONTH));
//          selEndDate.set(Calendar.DATE, selectedDate.get(Calendar.DATE));
//          selEndDate.set(Calendar.HOUR, selectedEtTime.get(Calendar.HOUR));
//          selEndDate.set(Calendar.MINUTE, selectedEtTime.get(Calendar.MINUTE));
//          selEndDate.set(Calendar.AM_PM, selectedEtTime.get(Calendar.AM_PM));

          quantity = Integer.parseInt(etQuantity.getText().toString());

          Consumption b = new Consumption(selectedMedicine.getMedId(), quantity, selStartDate.getTime());
          App.user.addConsumption(b.getMedId(), b.getQuantity(), b.getConDate(), getApplicationContext());

          Toast.makeText(AddConsumptionActivity.this, getString(R.string.save_successful), Toast.LENGTH_SHORT).show();
          finish();

        }
      }
    });
  }

  private boolean isValid() {
    boolean isValid = true;
    if (spnMember.getSelectedItemPosition() == 0) {
      Toast.makeText(this, R.string.mem_select_validation_msg, Toast.LENGTH_SHORT).show();
      isValid = false;
      }
// else if (spnFacility.getSelectedItemPosition() == 0) {
//      Toast.makeText(this, R.string.fac_select_validation_msg, Toast.LENGTH_SHORT).show();
//      isValid = false;
//    }

    try {
      Calendar selectedStTime = Calendar.getInstance();
      selectedStTime.setTime(timeFormatter.parse(etStrtTime.getText().toString()));
      Calendar convertedStTime = Calendar.getInstance();
      convertedStTime.set(Calendar.HOUR, selectedStTime.get(Calendar.HOUR));
      convertedStTime.set(Calendar.MINUTE, selectedStTime.get(Calendar.MINUTE));
      convertedStTime.set(Calendar.AM_PM, selectedStTime.get(Calendar.AM_PM));
    } catch (ParseException e) {
      Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show();
    }

    return isValid;
  }
}
