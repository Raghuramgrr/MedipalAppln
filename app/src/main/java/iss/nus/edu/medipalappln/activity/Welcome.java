package iss.nus.edu.medipalappln.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.dao.BioDataBaseAdapter;
import iss.nus.edu.medipalappln.fragment.IceDetails;
import iss.nus.edu.medipalappln.fragment.MeasurementFragment;
import iss.nus.edu.medipalappln.fragment.MeasureBloodPressureFragment;
import iss.nus.edu.medipalappln.fragment.PersonalBioForm;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    PersonalBioForm.OnFragmentInteractionListener,
                    IceDetails.OnFragmentInteractionListener,
                    MeasurementFragment.OnFragmentInteractionListener,
                    MeasureBloodPressureFragment.OnFragmentInteractionListener {
    public Session session;
    BioDataBaseAdapter bioDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomehome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            session=new Session(this);
            fragmentClass = PersonalBioForm.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.PersonalBio) {
            fragmentClass = PersonalBioForm.class;
        } else if (id == R.id.nav_ice) {
            fragmentClass = IceDetails.class;
        } else if (id == R.id.nav_measurement) {
            fragmentClass = MeasurementFragment.class;
        } else if (id == R.id.nav_appointment) {
            //fragmentClass = FragmentOne.class;
        } else if (id == R.id.nav_health_bio) {
            //fragmentClass = FragmentTwo.class;
        }
         else if (id == R.id.Logout) {
            //fragmentClass = FragmentTwo.class;
            logout();
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

   /* public void myClickMethod(View v){
        Fragmentone.myClickMethod(v);
    }
*/

    private void logout() {
        session.setLoggedin(Boolean.FALSE,session.username());
        finish();
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }
}
