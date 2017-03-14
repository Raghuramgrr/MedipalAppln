package iss.nus.edu.medipalappln;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import iss.nus.edu.medipalappln.medipal.Measurement;

public class MeasurementTest extends TestCase {

    String datePattern = "yyyy-MM-dd HH:mm";
    SimpleDateFormat simpleDateFormat;

    private Measurement measurementBloodPressure;
    private Measurement measurementPulse;
    private Measurement measurementTemperature;
    private Measurement measurementWeight;

    @Before public void setUp() throws Exception {

        simpleDateFormat = new SimpleDateFormat(pattern);

        measurementBloodPressure = new Measurement(1, 100, 80, simpleDateFormat.parse("2017-01-02 10:00"));
        measurementPulse = new Measurement(2, 90, simpleDateFormat.parse("2017-01-02 12:00"));
        measurementTemperature = new Measurement(3, 36, simpleDateFormat.parse("2017-01-02 14:00"));
        measurementWeight = new Measurement(4, 40, simpleDateFormat.parse("2017-01-02 18:00"));

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
