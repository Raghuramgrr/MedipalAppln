package iss.nus.edu.medipalappln.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.AddMedicineActivity;
import iss.nus.edu.medipalappln.adapter.MedicineListAdapter;

public class MedicineFragment extends Fragment {
  private MedicineListAdapter medicineListAdapter;
  private TextView tvEmpty;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View fragmentView = inflater.inflate(R.layout.fragment_medicine, container, false);
    ListView memberList = (ListView) fragmentView.findViewById(R.id.lv_member_list);
    tvEmpty = (TextView) fragmentView.findViewById(R.id.tv_empty_value);
    medicineListAdapter = new MedicineListAdapter(fragmentView.getContext());
    memberList.setAdapter(medicineListAdapter);
    FloatingActionButton floatingActionButton =
            (FloatingActionButton) fragmentView.findViewById(R.id.fab);
      floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), AddMedicineActivity.class));
      }
    });
    return fragmentView;
  }

  @Override
  public void onResume() {
    super.onResume();
    medicineListAdapter.refreshMembers();
    tvEmpty.setVisibility(medicineListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
  }
}
