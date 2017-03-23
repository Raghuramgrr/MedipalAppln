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

    private BloodPressure measurementBloodPressure;
    private Pulse measurementPulse;
    private Temperature measurementTemperature;
    private Weight measurementWeight;

    @Before public void setUp() throws Exception {

        testDate = new SimpleDateFormat(datePattern);

        /*measurementBloodPressure = new BloodPressure(100, 80, (String) testDate.parse("2017-01-02 11:00"));
        measurementPulse = new Pulse(90, (String) testDate.parse("2017-01-02 12:00"));
        measurementTemperature = new Temperature(36, (String) testDate.parse("2017-01-02 15:00"));
        measurementWeight = new Weight(41.10, (String) testDate.parse("2017-01-02 18:00"));*/

    }

    @After public void tearDown() throws Exception {
        measurementBloodPressure = null;
        measurementPulse = null;
        measurementTemperature = null;
        measurementWeight = null;
    }

    @Test public void testGetBloodPressure() {

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
