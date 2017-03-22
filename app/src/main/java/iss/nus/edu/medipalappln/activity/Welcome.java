package iss.nus.edu.medipalappln.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.dao.BioDataBaseAdapter;
import iss.nus.edu.medipalappln.dao.EmergencyDataBaseAdapter;
import iss.nus.edu.medipalappln.fragment.IceDetails;
import iss.nus.edu.medipalappln.fragment.MeasurementFragment;
import iss.nus.edu.medipalappln.fragment.PersonalBioForm;

/*<<<<<<< HEAD
import iss.nus.edu.medipalappln.dao.EmergencyDataBaseAdapter;
import iss.nus.edu.medipalappln.fragment.IceDetails;
import iss.nus.edu.medipalappln.fragment.PersonalBioForm;
public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PersonalBioForm.OnFragmentInteractionListener,
        IceDetails.OnFragmentInteractionListener, View.OnClickListener {
=======*/

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    PersonalBioForm.OnFragmentInteractionListener,
                    IceDetails.OnFragmentInteractionListener,
                    MeasurementFragment.OnFragmentInteractionListener,View.OnClickListener {
/*>>>>>>> eeec0a993cde1bf28bebd61b6f08e90393645978*/
    public Session session;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName,txtWebsite;
    private String path="";
    private Context context;
    public ArrayList<String> image_list=new ArrayList<String>();
    public ArrayList<Drawable> image_drawable=new ArrayList<Drawable>();

    BioDataBaseAdapter bioDataBaseAdapter;
    EmergencyDataBaseAdapter emergencyDataBaseAdapter;
    private static final int SELECT_PICTURE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_welcomehome);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // context=this.getApplicationContext();
        //bioDataBaseAdapter=new BioDataBaseAdapter(this);
        //bioDataBaseAdapter=bioDataBaseAdapter.open();
        // emergencyDataBaseAdapter=new EmergencyDataBaseAdapter(this);
        //emergencyDataBaseAdapter=emergencyDataBaseAdapter.open();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            session = new Session(this);
            fragmentClass = PersonalBioForm.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
           // loadNavHeader();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
/*<<<<<<< HEAD*/
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.username);
        // loadNavHeader();
        //txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        //imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.imageView);
        imgProfile.setClickable(true);
        try {
            imgProfile.setOnClickListener(this);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            //imgProfile.setOnClickListener((View.OnClickListener) this.getApplicationContext());

/*=======*/

        //user = new User(session.username());
        //user = new User("S12345678");
/*>>>>>>> eeec0a993cde1bf28bebd61b6f08e90393645978*/
    }


    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

   /* public void takePhoto()
    {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File folder = new File(Environment.getExternalStorageDirectory() + "/LoadImg");

        if(!folder.exists())
        {
            folder.mkdir();
        }
        final Calendar c = Calendar.getInstance();
        String new_Date= c.get(Calendar.DAY_OF_MONTH)+"-"+((c.get(Calendar.MONTH))+1)   +"-"+c.get(Calendar.YEAR) +" " + c.get(Calendar.HOUR) + "-" + c.get(Calendar.MINUTE)+ "-"+ c.get(Calendar.SECOND);
        path=String.format(Environment.getExternalStorageDirectory() +"/LoadImg/%s.png","LoadImg("+new_Date+")");
        File photo = new File(path);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photo));
        startActivityForResult(intent, 2);
    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    //Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    imgProfile.setImageURI(selectedImageUri);
                }
            }
        }
    }
    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }



    private void loadNavHeader() {
        // name, website
        txtName.setText(session.username());


        // loading header background image


        // Loading profile image

        //imgProfile.setImageResource(R.drawable.ic_medicine);
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.PersonalBio) {
            fragmentClass = PersonalBioForm.class;
        } else if (id == R.id.nav_ice) {
            try{fragmentClass = IceDetails.class;}
            catch (Exception e){e.printStackTrace();}
        }
        else if (id == R.id.nav_measurement) {
            //fragmentClass = MeasurementFragment.class;
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
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
        }


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
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        emergencyDataBaseAdapter.close();
    }

    @Override
    public void onClick(View v) {
        openImageChooser();
    }
}
