package iss.nus.edu.medipalappln;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import iss.nus.edu.medipalappln.medipal.BloodPressure;
import iss.nus.edu.medipalappln.medipal.Pulse;
import iss.nus.edu.medipalappln.medipal.Temperature;
import iss.nus.edu.medipalappln.medipal.Weight;

public class MeasurementTest extends TestCase {

    String datePattern = "yyyy-MM-dd HH:mm";
    SimpleDateFormat testDate;

    private BloodPressure bp;
    private Pulse pulse;
    private Temperature temperature;
    private Weight weight;

    @Before public void setUp() throws Exception {

        testDate = new SimpleDateFormat(datePattern);

        bp = new BloodPressure(100, 80, "2017-01-02 11:00");
        pulse = new Pulse(90, "2016-01-02 11:00");
        temperature = new Temperature(36, "2012-01-02 11:00");
        weight = new Weight(41.10, "2017-01-02 18:00");

    }

    @After public void tearDown() throws Exception {
        bp = null;
        pulse = null;
        temperature = null;
        weight = null;
    }

    @Test public void testGetBloodPressure() {
        BloodPressure bp2 = new BloodPressure(100, 80, "2017-01-02 11:00");
        String date = "2017-01-02 11:00";

        assertNotNull(bp);
        assertNotSame(bp, bp2); // different obj but same content
        assertEquals(date, 0, bp.getMeasuredOn().compareTo(date));
    }

    @Test public void testSetBloodPressure() {


    }

    @Test public void testGetPulse() {

    }

    @Test public void testSetPulse() {

    }

    @Test public void testGetTemperature() {

    }

    @Test public void testSetTemperature() {

    }

    @Test public void testGetWeight() {

    }

    @Test public void testSetWeight() {

    }

    @Test public void testShow() {

    }
}
