package iss.nus.edu.medipalappln;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import iss.nus.edu.medipalappln.medipal.Emergency;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by root on 26/3/17.
 */

public class ICEUnitTest extends TestCase {

    String datePattern = "yyyy-MM-dd HH:mm";
    SimpleDateFormat testDate;

    private Emergency bp1, bp2;
 /*   private Pulse pulse1, pulse2;
    private Temperature temperature1, temperature2;
    private Weight weight1, weight2;
*/
    @Before
    public void setUp() throws Exception {

        testDate = new SimpleDateFormat(datePattern);

        bp1 = new Emergency(100, "Raghu", "83419486" ,"1","Son");
        bp2 = new Emergency(101, "Ram", "83419487" ,"2","Friend");

    }

    @After
    public void tearDown() throws Exception {
        bp1 = null; bp2 = null;
           }

    @Test
    public void testICE() throws ParseException {
       /* String date = "2017-01-02 11:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        BloodPressure bpAfter = new BloodPressure(110, 90, "2016-01-22 12:00");
       */ ArrayList<Emergency> list1 = new ArrayList<Emergency>();
        ArrayList<Emergency> list2 = new ArrayList<Emergency>();

      //  Date d = df.parse(date);
 String name="";
        assertNotNull(bp1);
        assertNotEquals(bp1, bp2);

        assertEquals(name, 0, bp1.getName().compareTo(name));


        assertTrue(bp1.getName().equals("Raghu"));
        assertTrue(bp1.getName().equals("Ram"));

        list1.add(0, bp1);
        list1.add(1, bp2);
        //list1.add(2, bpAfter);
        list2.add(0, bp2);
        list2.add(1, bp1);
        list2.add(2, bp1);
        assertNotEquals(list1, list2);

        assertTrue(list1.get(0).equals(list2.get(1)));
    }

}
