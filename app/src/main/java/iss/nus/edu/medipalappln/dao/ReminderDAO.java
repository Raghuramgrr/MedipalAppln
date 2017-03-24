package iss.nus.edu.medipalappln.dao;

/**
 * Created by darryl on 26/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import iss.nus.edu.medipalappln.medipal.Reminder;

public class ReminderDAO extends DBDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.REMINDER.ID.toString() + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public ReminderDAO(Context context) {
        super(context);
    }

    public long save(Reminder reminder) {
        ContentValues values = new ContentValues();
        //values.put(DataBaseHelper.MID_COLUMN, member.getMemberNumber());
        values.put(DataBaseHelper.REMINDER.Frequency.toString(), reminder.getFrequency());
        values.put(DataBaseHelper.REMINDER.StartTime.toString(), reminder.getStartTime());
        values.put(DataBaseHelper.REMINDER.Interval.toString(), reminder.getInterval());

        return database.insert(DataBaseHelper.TABLE_REMINDER, null, values);
    }

    public long update(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.REMINDER.Frequency.toString(), reminder.getFrequency());
        values.put(DataBaseHelper.REMINDER.StartTime.toString(), reminder.getStartTime());
        values.put(DataBaseHelper.REMINDER.Interval.toString(), reminder.getInterval());

        long result = database.update(DataBaseHelper.TABLE_REMINDER, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(reminder.getRemindId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Reminder reminder) {
        return database.delete(DataBaseHelper.TABLE_REMINDER, WHERE_ID_EQUALS,
                new String[] { reminder.getRemindId() + "" });
    }

    public Reminder getReminder() {
        Reminder reminder = null;

        ArrayList<Integer> list = new ArrayList<Integer>();


        int rid1 = -1;
        String sql = "SELECT max(rem_id) FROM reminder_table";
             //   + " WHERE " + DataBaseHelper.REM_ID + " = ?";

        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.getCount()==1) {
            cursor.moveToFirst();
            rid1 = cursor.getInt(0);
        }
        int id = list.get(0);

        String sql2 = "SELECT * FROM " + DataBaseHelper.TABLE_REMINDER
                + " WHERE " + DataBaseHelper.REMINDER.ID.toString() + " = ?";

        Cursor cursor2 = database.rawQuery(sql2, new String[] { id + "" });

        if (cursor2.moveToNext()) {
            int rid = cursor2.getInt(0);
            String rfreq = cursor2.getString(1);
            String rStTime = cursor2.getString(2);
            String rInterval = cursor2.getString(3);

            reminder = new Reminder(rid, rfreq, rStTime, rInterval);

        }


        return reminder;
    }


    //Retrieves a single member record with the given id
    public Reminder getReminder(long id) {
        Reminder reminder = null;

        String sql = "SELECT * FROM " + DataBaseHelper.TABLE_REMINDER
                + " WHERE " + DataBaseHelper.REMINDER.ID.toString() + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            int rid = cursor.getInt(0);
            String rfreq = cursor.getString(1);
            String rStTime = cursor.getString(2);
            String rInterval = cursor.getString(3);

            reminder = new Reminder(rid, rfreq, rStTime, rInterval);

        }
        return reminder;
    }

    public int getMaxReminderId() {
        int maxRemindId = -1;
        String sql = "SELECT MAX(rem_id) from reminder_table";

        Cursor cursor = database.rawQuery(sql, null);
        if(cursor.getCount()== 1){
            cursor.moveToFirst();
            maxRemindId = cursor.getInt(0);
        }

        return maxRemindId;
    }
}
