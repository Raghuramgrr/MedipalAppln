package iss.nus.edu.medipalappln;

/**
 * Created by root on 7/3/17.
 */

import android.content.Context;
import android.content.SharedPreferences;


public class Session {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    static public Boolean loggedin=false;
    Context ctx;
    public Session(Context context) {
        this.ctx=context;
        preferences=ctx.getSharedPreferences("Login",Context.MODE_PRIVATE);
        editor=preferences.edit();

    }

    public void setLoggedin(Boolean Loggedin){
        editor.putBoolean("loggedIN",Loggedin);
        editor.commit();
        /*f(Loggedin==Boolean.TRUE)
        {
            loggedin=true;
        }*/
    }
   public boolean loggedin(){
        return preferences.getBoolean("loggedIN",false);
    }
}