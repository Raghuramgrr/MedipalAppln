package iss.nus.edu.medipalappln.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import iss.nus.edu.medipalappln.Encryption.CeaserCipher;
import iss.nus.edu.medipalappln.R;
import iss.nus.edu.medipalappln.dao.LoginDataBaseAdapter;

public class HomeActivity extends Activity
{
    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Session session;
    TextView register;
    CeaserCipher cipher;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session=new Session(this);
        first_time_check();
        try {
            setContentView(R.layout.activity_home);
            loginDataBaseAdapter = new LoginDataBaseAdapter(this);
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        //btnSignUp = (Button) findViewById(R.id.buttonSignUP);
        cipher=new CeaserCipher();
        register=(TextView)findViewById(R.id.buttonSignUp);
        SpannableString styledString = new SpannableString("Personal MediPal");
        styledString.setSpan(new StyleSpan(Typeface.BOLD), 0, 7, 0);
        styledString.setSpan(new StyleSpan(Typeface.ITALIC), 8, 14, 0);

        //TextView tv = (TextView)findViewById(R.id.tv);
        //tv.setText(styledString);
        session=new Session(this);
        final EditText editTextUserName=(EditText)findViewById(R.id.input_email);
        final EditText editTextPassword=(EditText)findViewById(R.id.input_password);
try {
    btnSignIn.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
                /*Intent intentSignIn = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intentSignIn);*/
            // get The User name and Password
            String userName = editTextUserName.getText().toString();
            String password = editTextPassword.getText().toString();

            // fetch the Password form database for respective user name
            String storedPassword = loginDataBaseAdapter.getSingleEntry(userName);
            String decodedPasswd = cipher.decode(storedPassword, 5);

            // check if the Stored password matches with  Password entered by user
            if (password.equals(decodedPasswd)) {
                session.setLoggedin(Boolean.TRUE, userName);
                Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                //Intent in = new Intent(getApplicationContext(), Recyclerview.class);
                Intent in=new Intent(getApplicationContext(),Welcome.class);
               // Intent in=new Intent(getApplicationContext(),contacts.class);
//Intent in =new Intent(getApplicationContext(), FingerprintActivity.class);
                startActivity(in);
                //dialog.dismiss();

            } else {
                Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
            }


        }
    });
}
catch (Exception e){
    e.printStackTrace();
}

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentSignUp);

            }
        });

        }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

    private boolean first_time_check() {
        if((session.loggedin())){
            try {
                //Intent i = new Intent(HomeActivity.this, Welcome.class);
                //Intent i = new Intent(HomeActivity.this, contacts.class);
                Intent i = new Intent(HomeActivity.this, Welcome.class);
                startActivity(i);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finish();
        }
        return false;

    }
}