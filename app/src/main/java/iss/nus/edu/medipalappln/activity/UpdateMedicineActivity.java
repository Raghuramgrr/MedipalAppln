package iss.nus.edu.medipalappln.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

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
 * Created by rama on 3/21/2017.
 */

public class UpdateMedicineActivity extends AppCompatActivity{

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();


    EditText editName,editDescription,editCategory,editRemind,editQuantity,editDosage,editThreshold,editDateIssued,
            editExpiryFactor;
    View mView;
    Button updateBtn;
    int quantity, dosage, threshold, expiryFactor;
    int catId, remID;
    Context context;
    List<Category> categoryList = null;
    Spinner spnFacility;
    Switch reminder;
    String remindFlag = "FALSE";
    LinearLayout reminderButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        mView = getLayoutInflater().inflate(R.layout.fragment_medicine, null);

        spnFacility = (Spinner) findViewById(R.id.spn_facility_1);
        reminder = (Switch) findViewById(R.id.rem_switch1);

        editName = (EditText) findViewById(R.id.et_medName);
        editDescription = (EditText) findViewById(R.id.et_medDesc);

        editQuantity = (EditText) findViewById(R.id.et_medQuantity);
        editDosage = (EditText) findViewById(R.id.et_medDosage);
        editThreshold = (EditText) findViewById(R.id.et_medThreshold);
        editDateIssued = (EditText) findViewById(R.id.et_medDateIssued);
        editExpiryFactor = (EditText) findViewById(R.id.et_medExpiryFactor);

        Intent intent = getIntent();
        final int medId = intent.getExtras().getInt("medId");

        final Medicine m1 = App.user.getMember(medId,context);
        final Reminder r1 = App.user.getReminder(m1.getRemindId(),context);

        if(m1.getRemindFlag().equalsIgnoreCase("TRUE")) {
            reminder.setChecked(r1.getRemindId() != 0);
        }else{
            reminderButton = (LinearLayout) findViewById(R.id.Text4);
            reminderButton.setVisibility(View.INVISIBLE);
        }

        Category c1 = App.user.getFacility(m1.getCatId(),context);
        catId = c1.getCatId();

        final int remId = r1.getRemindId();

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


        editName.setText(m1.getMedName());
        editDescription.setText(m1.getMedDesc());

        editQuantity.setText(String.valueOf(m1.getQuantity()));
        editDosage.setText(String.valueOf(m1.getDosage()));
        editThreshold.setText(String.valueOf(m1.getThreshold()));
        editDateIssued.setText(String.valueOf(m1.getDateIssued()));
        editExpiryFactor.setText(String.valueOf(m1.getExpireFactor()));


        updateBtn = (Button) findViewById(R.id.btn_med_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    quantity = Integer.valueOf(editQuantity.getText().toString());
                    dosage = Integer.parseInt(editDosage.getText().toString());
                    threshold = Integer.parseInt(editThreshold.getText().toString());
                    expiryFactor = Integer.parseInt(editExpiryFactor.getText().toString());
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if(!reminder.isChecked()) {
                    Medicine m = new Medicine(medId, editName.getText().toString(), editDescription.getText().toString(),
                            catId, 0, "FALSE",
                            quantity, dosage, threshold,
                            selectedDate.getTime(), expiryFactor);

                    App.user.updateMember(m, getApplicationContext());
                    finish();
                }else{
                    Medicine m = new Medicine(medId, editName.getText().toString(), editDescription.getText().toString(),
                            catId, r1.getRemindId(),m1.getRemindFlag(),
                            quantity, dosage, threshold,
                            selectedDate.getTime(), expiryFactor);
                }
            }
        });
    }

}
