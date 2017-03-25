package iss.nus.edu.medipalappln.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.AddConsumptionActivity;
import iss.nus.edu.medipalappln.adapter.ConsumptionListAdapter;


// for searching - implements SearchView.OnQueryTextListener
public class ConsumptionFragment extends Fragment implements SearchView.OnQueryTextListener {
  private TextView tvEmpty;
  private ConsumptionListAdapter consumptionListAdapter;
  Spinner spnMember;

  // for searching
  SearchView editsearch;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    final View fragmentView = inflater.inflate(R.layout.fragment_booking, container, false);
    ListView bookingList = (ListView) fragmentView.findViewById(R.id.lv_booking_list);
    consumptionListAdapter = new ConsumptionListAdapter(getActivity());
    bookingList.setAdapter(consumptionListAdapter);

    spnMember = (Spinner) fragmentView.findViewById(R.id.filter);
    List<String> spnMemList = new ArrayList<>();
    ArrayAdapter spnMemAdapter =
            ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                    R.array.filter_cat, android.R.layout.simple_spinner_item);
    if(spnMemAdapter == null){
        Log.d("!!", "it's null");}
      spnMember.setAdapter(spnMemAdapter);
      spnMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          Log.d("mark", i + " voke");
          //TODO filter
          if (i > 0){
            ListView bookingList = (ListView) fragmentView.findViewById(R.id.lv_booking_list);
            consumptionListAdapter = new ConsumptionListAdapter(getActivity());
            bookingList.setAdapter(consumptionListAdapter);
            consumptionListAdapter.refreshBookings();
          }
          else{
            ListView bookingList = (ListView) fragmentView.findViewById(R.id.lv_booking_list);
            consumptionListAdapter = new ConsumptionListAdapter(getActivity());
            bookingList.setAdapter(consumptionListAdapter);
            consumptionListAdapter.refreshBookings();
          }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
      });
    tvEmpty = (TextView) fragmentView.findViewById(R.id.tv_empty_value);
    FloatingActionButton floatingActionButton =
        (FloatingActionButton) fragmentView.findViewById(R.id.fab);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getActivity().startActivity(new Intent(getActivity(), AddConsumptionActivity.class));
      }
    });

    return fragmentView;
  }

  @Override
  public void onResume() {
    super.onResume();
    consumptionListAdapter.refreshBookings();
    tvEmpty.setVisibility(consumptionListAdapter.getCount() == 0 ? View.VISIBLE : View.GONE);
  }

  // for searching - OnQueryTextListener implement methods
  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  // for searching - OnQueryTextListener implement methods
  @Override
  public boolean onQueryTextChange(String newText) {
    String text = newText;
    consumptionListAdapter.filter(text);
    return false;
  }
}
