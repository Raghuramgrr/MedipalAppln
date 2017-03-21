package iss.nus.edu.medipalappln.medipal;

import android.app.Application;

public class App extends Application {
    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
    }
}
