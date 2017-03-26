package iss.nus.edu.medipalappln.validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Raghu on 22/3/17.
 */

public class FormValidation {

    final public int minage=10;
    final public int maxage=120;
    public static boolean weightValidator(CharSequence weight) {
        if (weight.length() < 5 || weight.length() > 200)
            return false;
        else
            return true;

    }

    public static boolean heightValidator(CharSequence height) {

        if (height.length() < 50 || height.length() > 220)
            return false;
        else
            return true;


    }


    public static boolean emailValidator(CharSequence email) {

        Pattern pattern1 = Pattern.compile("^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+([a-zA-Z])+");

        Matcher matcher1 = pattern1.matcher(email);

        return matcher1.matches();

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static boolean phoneValidator(CharSequence phone) {
        if (phone.length() < 6 || phone.length() > 12)
            return false;
        else
            return true;
    }



        public static boolean isDOBInRange(String dateStr) {
            try {
                Calendar calMin = Calendar.getInstance();
                calMin.add(Calendar.YEAR, -120); //24
                Date dateStart = calMin.getTime();

                Calendar calMax = Calendar.getInstance();
                calMax.add(Calendar.YEAR, -10); //18
                Date dateEnd = calMax.getTime();

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Date date = format.parse(dateStr);

                if (date != null && dateStart != null && dateEnd != null) {
                    if (date.after(dateStart) && date.before(dateEnd)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        }

    public static boolean isBloodGroupValid(CharSequence bloodGroup){
        if(bloodGroup != null) {

            String[] bloodgps = new String[]{
                    "0+",
                    "0-",
                    "A+",
                    "A-",
                    "B+",
                    "B-",
                    "AB+",
                    "AB-"



            };
            int len = bloodgps.length;
            for (int i = 0; i < len; i++) {
                if(bloodGroup.equals(bloodgps[i]))
                    return true;
            }
        }return false;
    }


    public static boolean pincodeValidator(CharSequence pincode) {
        String pat1;

        Pattern pattern1 = Pattern.compile("^[1-9][0-9]{6}$");

        Matcher matcher1 = pattern1.matcher(pincode);

        return matcher1.matches();

    }



    public static boolean nricValidator(CharSequence nric) {
        String pat1;

        Pattern pattern1 = Pattern.compile("^[STFG]\\d{7}[A-Z]$");

        Matcher matcher1 = pattern1.matcher(nric);

        return matcher1.matches();

    }



}




