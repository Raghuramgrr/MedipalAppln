package iss.nus.edu.medipalappln;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;

import iss.nus.edu.medipalappln.medipal.App;
import iss.nus.edu.medipalappln.medipal.Appointment;
import iss.nus.edu.medipalappln.medipal.BloodPressure;
import iss.nus.edu.medipalappln.medipal.Pulse;
import iss.nus.edu.medipalappln.medipal.Temperature;
import iss.nus.edu.medipalappln.medipal.Weight;

/**
 * Created by cherry on 2017/3/24.
 */

public class AppointmentTest extends TestCase{

    String datePattern = "yyyy-MM-dd HH:mm";
    SimpleDateFormat dateTest;

    private Appointment app1;

    @Before public void init() throws Exception {

        dateTest = new SimpleDateFormat(datePattern);

        app1 = new Appointment("Shanghai","2017-06-28 14:00","Come back home");
    }

    @After public void clear() throws Exception {
        app1 = null;
    }

    @Test public void testGetAppointment(){
        assertNull(app1);
        Appointment app2 = new Appointment("Chengdu","2017-06-25 15:00","Time to go back");
        assertNotSame(app1,app2);
    }

    @Test public void testSetAppointment(){
        Appointment app3 = null;
        assertNull(app3);
        app3 = new Appointment("Shanghai","2018-07-03 09:00","Modu");
        assertNotNull(app3);
        assertNotSame(app1,app3);
    }
}
