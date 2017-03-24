package iss.nus.edu.medipalappln.application;

import android.app.Application;

import iss.nus.edu.medipalappln.medipal.Club;


/**
 * Created by swarna on 9/8/16.
 */
public class App extends Application {
  public static Club club;

  @Override
  public void onCreate() {
    super.onCreate();
    club = new Club();

  }
}
