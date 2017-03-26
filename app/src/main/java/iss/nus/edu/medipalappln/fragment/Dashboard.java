package iss.nus.edu.medipalappln.fragment;

/**
 * Created by Raghu on 7/3/17.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.activity.DashboardAdapter;
import iss.nus.edu.medipalappln.activity.dashboardcontent;

public class Dashboard extends Fragment implements IceDetails.OnFragmentInteractionListener {
    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private List<dashboardcontent> dashboardcontentList;
    private static final String TAG = "Dashboard";
 private Context _context;
    private ImageButton ibtn_Measurement;

    private OnFragmentInteractionListener mListener;




    public Dashboard() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

        @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
            setHasOptionsMenu(true);
    View view = inflater.inflate(R.layout.recyclerview, container, false);
ViewGroup fragmentview=(ViewGroup)getView();
    dashboardcontentList = new ArrayList<>();
    adapter = new DashboardAdapter(getActivity().getApplicationContext(), dashboardcontentList);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
    recyclerView.setLayoutManager(mLayoutManager);
            FragmentManager fragmentManager=getFragmentManager();

    recyclerView.addItemDecoration(new Dashboard.GridSpacingItemDecoration(2, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter);

    prepareView();

    try {
        Glide.with(this).load(R.drawable.cover).into((ImageView) getActivity().findViewById(R.id.backdrop));
    } catch (Exception e) {
        e.printStackTrace();
    }


    return view;
}



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  callICE();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (Dashboard.OnFragmentInteractionListener) context;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*public void callICE() {
         Fragment fragment=null;
        Class classone=null;
        IceDetails fragment2 = new IceDetails();
        classone=IceDetails.class;
        //IceDetails ice=new IceDetails();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContent,ice );
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
      //  FragmentManager fragmentManager = getFragmentManager();
        //FragmentTransaction fragmentTransaction =
        //        fragmentManager.beginTransaction().replace(R.id.recycler_view,fragment2).commit();
        //fragmentTransaction.replace(R.id.recycler_view, fragment2);
        //fragmentTransaction.commit();
        /*Class fragmentClass=null;

       fragmentClass=IceDetails.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/
                /*Class fragmentclass= IceDetails.class;
                try {
                    fragment = (Fragment) fragmentclass.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
        IceDetails fragment1=new IceDetails();

                FragmentManager fragmentManager= getActivity().getSupportFragmentManager();
        FragmentTransaction fragmenttransaction=fragmentManager.beginTransaction();
        fragmenttransaction.replace(R.id.recycler_view, fragment).commit();
        fragmenttransaction.addToBackStack(null);
        fragmenttransaction.commit();*/



        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
       // callICE();


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void prepareView() {
        //DashBoard images tbd by kiruba
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album2,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8

        };



        dashboardcontent a = new dashboardcontent("Add PersonalBio", covers[0]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Add Medication",  covers[1]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Add Appointment 5", covers[2]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Doctor", covers[3]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Doct visit", covers[4]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Measurements",covers[5]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("Measurements",  covers[6]);
        dashboardcontentList.add(a);

        a = new dashboardcontent("PersonalBio", covers[7]);
        dashboardcontentList.add(a);




        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public void onClickMeasurement() {

    }
}
