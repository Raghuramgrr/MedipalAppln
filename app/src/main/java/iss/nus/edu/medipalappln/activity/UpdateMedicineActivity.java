package iss.nus.edu.medipalappln.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import iss.nus.edu.medipalappln.medipal.Medicine;
import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;

/**
 * Created by rama on 3/21/2017.
 */

public class UpdateMedicineActivity extends AppCompatActivity {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    Calendar currentCal = Calendar.getInstance();
    Calendar selectedDate = Calendar.getInstance();


    EditText editName,editDescription,editCategory,editRemind,editQuantity,editDosage,editThreshold,editDateIssued,
            editExpiryFactor;
    View mView;
    Button updateBtn;
    int quantity, dosage, threshold, expiryFactor;
    int catId, remID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        mView = getLayoutInflater().inflate(R.layout.fragment_medicine, null);

        Intent intent = getIntent();

        final int medId = intent.getExtras().getInt("medId");
        final String medName = intent.getExtras().getString("medName");
        final String medDesc = intent.getExtras().getString("medDesc");
        final int medCat = intent.getExtras().getInt("medCat");
        final int medRemind = intent.getExtras().getInt("medRemind");
        final int medQuantity = intent.getExtras().getInt("medQuantity");
        final int medDosage = intent.getExtras().getInt("medDosage");
        final int medThreshold = intent.getExtras().getInt("medThreshold");
        final String medDateIssued = intent.getExtras().getString("medDateIssued");
        final int medExpiryFactor = intent.getExtras().getInt("medExpiryFactor");


        editName.setText(medName);
        editDescription.setText(medDesc);
        editCategory.setText(medCat);
        editRemind.setText(medRemind);
        editQuantity.setText(medQuantity);
        editDosage.setText(medDosage);
        editThreshold.setText(medThreshold);
        editDateIssued.setText(medDateIssued);
        editExpiryFactor.setText(medExpiryFactor);


        editDateIssued.setText(dateFormatter.format(selectedDate));
        editDateIssued.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        updateBtn = (Button) findViewById(R.id.btn_med_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName = (EditText) findViewById(R.id.et_medName);
                editDescription = (EditText) findViewById(R.id.et_medDesc);
           //     editCategory = (EditText) findViewById(R.id.et_medCat);
           //     editRemind = (EditText) findViewById(R.id.et_medRemind);
                editQuantity = (EditText) findViewById(R.id.et_medQuantity);
                editDosage = (EditText) findViewById(R.id.et_medDosage);
                editThreshold = (EditText) findViewById(R.id.et_medThreshold);
                editDateIssued = (EditText) findViewById(R.id.et_medDateIssued);
                editExpiryFactor = (EditText) findViewById(R.id.et_medExpiryFactor);

                try {
            quantity = Integer.valueOf(editQuantity.getText().toString()).intValue();
            dosage = Integer.parseInt(editDosage.getText().toString());
            threshold = Integer.parseInt(editThreshold.getText().toString());
            expiryFactor = Integer.parseInt(editExpiryFactor.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

                Medicine m = new Medicine(medId, editName.getText().toString(), editDescription.getText().toString(),
                        catId,remID,
                        quantity, dosage,threshold,
                        selectedDate.getTime(),expiryFactor);

                App.user.updateMember(m, getApplicationContext());
                finish();
            }
        });
    }

}
