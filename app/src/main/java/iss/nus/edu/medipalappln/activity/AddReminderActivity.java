package iss.nus.edu.medipalappln.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Reminder;

public class AddReminderActivity extends AppCompatActivity {
  private EditText etReminderFreq, etReminderStTime, etReminderInterval;

  private Reminder reminder;

  long remId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_reminder);

      etReminderFreq = (EditText) findViewById(R.id.et_remind_freq);
      etReminderStTime = (EditText) findViewById(R.id.et_select_start_time);
      etReminderInterval = (EditText) findViewById(R.id.et_remindInterval);

/*
    reminder = new Reminder(etReminderFreq.getText().toString(), etReminderStTime.getText().toString(),
            etReminderInterval.getText().toString());
*/

    final int remId = App.user.getMaxReminderId(this);


    Button btnSave = (Button) findViewById(R.id.btn_save_remind);
    btnSave.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isValid()) {
/*
          App.user.addReminder(etReminderFreq.getText().toString(), etReminderStTime.getText().toString(),
                  etReminderInterval.getText().toString(), getApplicationContext());
*/

          /*Intent intent = new Intent(getApplicationContext(), AddMedicineActivity.class);
          intent.putExtra("remId",remId);
          getApplicationContext().startActivity(intent);
*/
          Toast.makeText(AddReminderActivity.this, "Reminder Set!", Toast.LENGTH_SHORT).show();
          finish();
        }
      }
    });
  }

  private boolean isValid() {
    boolean isValid = true;
    if (TextUtils.isEmpty(etReminderFreq.getText().toString().trim())) {
      etReminderFreq.setError(getString(R.string.first_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etReminderStTime.getText().toString().trim())) {
      etReminderStTime.setError(getString(R.string.second_name_validation_msg));
      isValid = false;
    }
    if (TextUtils.isEmpty(etReminderInterval.getText().toString().trim())) {
      etReminderInterval.setError(getString(R.string.sur_name_validation_msg));
      isValid = false;
    }
    return isValid;
  }
}
