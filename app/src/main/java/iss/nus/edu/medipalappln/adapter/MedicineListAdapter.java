package iss.nus.edu.medipalappln.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.medipal.Category;
import iss.nus.edu.medipalappln.medipal.Medicine;
import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.UpdateMedicineActivity;
import iss.nus.edu.medipalappln.application.App;

/**
 * Created by Swarna on 8/6/2016.
 */
public class MedicineListAdapter extends ArrayAdapter<Medicine> {
  private Context context;
  private List<Medicine> medicines = new ArrayList<>();
    Category category;

  public MedicineListAdapter(Context context) {
    super(context, R.layout.medicine_row_layout);
    this.context = context;
    refreshMembers();
  }

  @Override
  public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
    if (convertView == null) {
      LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.medicine_row_layout, parent, false);
      viewHolder = new ViewHolder();
        viewHolder.tvMedId = (TextView) convertView.findViewById(R.id.tv_medId);
      viewHolder.tvMedName = (TextView) convertView.findViewById(R.id.tv_medName);
      viewHolder.tvMedDesc = (TextView) convertView.findViewById(R.id.tv_medDesc);
      viewHolder.tvMedCat = (TextView) convertView.findViewById(R.id.tv_medCode);
      viewHolder.btnMedUpdate = (Button) convertView.findViewById(R.id.btn_med_update);
      viewHolder.btnMedRemove = (Button) convertView.findViewById(R.id.btn_med_delete);
      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    final Medicine medicine = medicines.get(position);
      Log.d("Niv", String.valueOf(medicine.getMedId()));
    final Medicine m1 = App.club.getMember(medicine.getMedId());
    viewHolder.tvMedName.setText(medicine.getMedName().toString());
    viewHolder.tvMedDesc.setText(medicine.getMedDesc().toString());

      category = App.club.getFacility(medicine.getCatId(), getContext());

    viewHolder.tvMedCat.setText(category.getName());
/*    viewHolder.tvMedReminder.setText(medicine.getReminder().toString());
    viewHolder.tvMedQuantity.setText(medicine.getQuantity());
    viewHolder.tvMedDosage.setText(medicine.getDosage());
    viewHolder.tvMedThreshold.setText(medicine.getThreshold());
    viewHolder.tvMedDateIssued.setText(medicine.getDateIssued());
    viewHolder.tvMedExpireFac.setText(medicine.getExpireFactor());*/

      final String msg = "Medicine is deleted";
      final String msgRestore = "Medicine restored";

    viewHolder.btnMedRemove.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        App.club.removeMember(medicine, getContext());
        refreshMembers();

/*        Snackbar snackbar = Snackbar.make(coordinatorLayout,,Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                    Snackbar snackbar1 = Snackbar.make(coordinatorLayout,@,Snackbar.LENGTH_SHORT);
                    snackbar1.show();
                  }
                });

        snackbar.show();*/
      }
    });


      viewHolder.btnMedUpdate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent intent = new Intent(context, UpdateMedicineActivity.class);
              intent.putExtra("medId", m1.getMedId());
              intent.putExtra("medName", m1.getMedName());
              intent.putExtra("medDesc", m1.getMedDesc());
              intent.putExtra("medCat", m1.getCatId());
              intent.putExtra("medRemind", m1.getRemindId());
              intent.putExtra("medQuantity", m1.getQuantity());
              intent.putExtra("medDosage", m1.getDosage());
              intent.putExtra("medThreshold", m1.getThreshold());
              intent.putExtra("medDateIssued", m1.getDateIssued().toString());
              intent.putExtra("medExpiryFactor", m1.getExpireFactor());

              context.startActivity(intent);


              /*App.club.updateMember(medicine,getContext());
              refreshMembers();*/
          }
      });


    return convertView;
  }

  public void refreshMembers() {
    medicines.clear();
    medicines.addAll(App.club.getMedicines(this.context));
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return medicines.size();
  }

  static class ViewHolder {
    TextView tvMedId, tvMedName, tvMedDesc, tvMedCat, tvMedReminder, tvMedQuantity, tvMedDosage, tvMedThreshold, tvMedDateIssued, tvMedExpireFac;
    Button btnMedUpdate, btnMedRemove;
  }
}
