package iss.nus.edu.medipalappln.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MediPal.db";

    public static final String TABLE_PERSONALBIO = "PersonalBio";
    public static final String TABLE_HEALTHBIO = "HealthBio";
    public static final String TABLE_CATEGORY = "Categories";
    public static final String TABLE_MEDICINE = "Medicine";
    public static final String TABLE_MEASUREMENT = "Measurement";
    public static final String TABLE_CONSUMPTION = "Consumption";
    public static final String TABLE_REMINDER = "Reminders";
    public static final String TABLE_APPOINTMENT = "Appointment";
    public static final String TABLE_ICE= "ICE";

    //table columns
    public enum PERSONALBIO {ID, Name, DOB, IDNo, Address, PostalCode, Height, BloodType};
    public enum HEALTHBIO {ID, Condition, StartDate, ConditionType};
    public enum CATEGORY {ID, Category, Code, Description, Remind};
    public enum MEDICINE {ID, Medicine, Description, CatID, ReminderID, Remind, Quantity, Dosage,
        ConsumeQualty, Threshold, DateIssued, ExpireFactor};
    public enum MEASUREMENT {ID, Systolic, Diastolic, Pulse, Temperature, Weight , MeasuredOn};
    public enum CONSUMPTION {ID, MedicineID, Quantity, ConsumedOn};
    public enum REMINDER {ID, Frequency, StartTime, Interval};
    public enum APPOINTMENT {ID, Location, Appointment, Description};
    public enum ICE {ID, Name, ContactNo, ContactType, Description, Sequence};

    //begin SQL statement
    public static final String CREATE_TABLE_PERSONALBIO = "CREATE TABLE " + TABLE_PERSONALBIO +
            "(" +
            PERSONALBIO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PERSONALBIO.Name + " VARCHAR(100), " +
            PERSONALBIO.DOB + " DATE, " +
            PERSONALBIO.IDNo + " VARCHAR(20), " +
            PERSONALBIO.Address + " VARCHAR(100), " +
            PERSONALBIO.PostalCode + " VARCHAR(10), " +
            PERSONALBIO.Height + " INTEGER, " +
            PERSONALBIO.BloodType + " VARCHAR(10) " +
            ");";

    public static final String CREATE_TABLE_HEALTHBIO = "CREATE TABLE " + TABLE_HEALTHBIO +
            "(" +
            HEALTHBIO.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HEALTHBIO.Condition + " VARCHAR(255), " +
            HEALTHBIO.StartDate + " DATE, " +
            HEALTHBIO.ConditionType + " VARCHAR(1) " +
            ");";

    public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY +
            "(" +
            CATEGORY.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATEGORY.Category + " VARCHAR(50), " +
            CATEGORY.Code + " VARCHAR(5), " +
            CATEGORY.Description + " VARCHAR(255), " +
            CATEGORY.Remind + " INTEGER DEFAULT 0" + //there is no boolean type in sqlite
            ");";

    public static final String CREATE_TABLE_MEDICINE = "CREATE TABLE " + TABLE_MEDICINE +
            "(" +
            MEDICINE.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MEDICINE.Medicine + " VARCHAR(50), " +
            MEDICINE.Description + " VARCHAR(255), " +
            MEDICINE.CatID + " INTEGER, " +
            MEDICINE.ReminderID + " INTEGER, " +
            MEDICINE.Remind + " INTEGER DEFAULT 0, " +
            MEDICINE.Quantity + " INTEGER, " +
            MEDICINE.Dosage + " INTEGER, " +
            MEDICINE.ConsumeQualty + " INTEGER, " +
            MEDICINE.Threshold + " INTEGER, " +
            MEDICINE.DateIssued  + " DATE, " +
            MEDICINE.ExpireFactor  + " INTEGER " +
            ");";

    public static final String CREATE_TABLE_MEASUREMENT = "CREATE TABLE " + TABLE_MEASUREMENT +
            "(" +
            MEASUREMENT.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MEASUREMENT.Systolic + " INTEGER, " +
            MEASUREMENT.Diastolic + " INTEGER, " +
            MEASUREMENT.Pulse + " INTEGER, " +
            MEASUREMENT.Temperature + " DECIMAL(5,2), " +
            MEASUREMENT.Weight + " INTEGER, " +
            MEASUREMENT.MeasuredOn + " DATETIME" +
            ");";

    //test data
    public static final String INSERT_TABLE_MEASUREMENT = "INSERT INTO " + TABLE_MEASUREMENT +
            "(" +
            MEASUREMENT.Systolic + ", " +
            MEASUREMENT.Diastolic + ") " +
            "VALUES (100, 85);";

    public static final String CREATE_TABLE_CONSUMPTION = "CREATE TABLE " + TABLE_CONSUMPTION +
            "(" +
            CONSUMPTION.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CONSUMPTION.MedicineID + " INTEGER, " +
            CONSUMPTION.Quantity + " INTEGER, " +
            CONSUMPTION.ConsumedOn + " DATETIME " +
            ");";

    public static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER +
            "(" +
            REMINDER.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            REMINDER.Frequency + " INTEGER, " +
            REMINDER.StartTime + " DATETIME, " +
            REMINDER.Interval + " INTEGER " +
            ");";

    public static final String CREATE_TABLE_APPOINTMENT = "CREATE TABLE " + TABLE_APPOINTMENT +
            "(" +
            APPOINTMENT.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            APPOINTMENT.Location + " VARCHAR(100), " +
            APPOINTMENT.Appointment + " DATETIME, " +
            APPOINTMENT.Description + " VARCHAR(255) " +
            ");";

    public static final String CREATE_TABLE_ICE = "CREATE TABLE " + TABLE_ICE +
            "(" +
            ICE.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ICE.Name + " VARCHAR(100), " +
            ICE.ContactNo + " VARCHAR(20) ," +
           ICE.ContactType + " INTEGER, " +
            ICE.Description + " VARCHAR(255) " +
           // ICE.Sequence + " INTEGER " +
            ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    //end of SQL statements

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DataBaseHelper(context);
        }
        return instance;
    }

    //TODO: Change to private. Create table in DataBaseHelper in ONLY.
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        /*try {
            _db.execSQL(EmergencyDataBaseAdapter.DATABASE_CREATE_Emergency);
        }
        catch (SQLException e){
            e.printStackTrace();
        }*/
try {
    _db.execSQL(CREATE_TABLE_PERSONALBIO);
    _db.execSQL(CREATE_TABLE_HEALTHBIO);
    _db.execSQL(CREATE_TABLE_CATEGORY);
    _db.execSQL(CREATE_TABLE_MEDICINE);
    _db.execSQL(CREATE_TABLE_MEASUREMENT);
    _db.execSQL(CREATE_TABLE_CONSUMPTION);
    _db.execSQL(CREATE_TABLE_REMINDER);
    _db.execSQL(CREATE_TABLE_APPOINTMENT);
    _db.execSQL(CREATE_TABLE_ICE);
}
catch (Exception e){
    e.printStackTrace();
}

        //_db.execSQL(INSERT_TABLE_MEASUREMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
/*<<<<<<< HEAD*/
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
       /* if(_newVersion>_oldVersion) {
            try {
                _db.execSQL(EmergencyDataBaseAdapter.DATABASE_CREATE_Emergency);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.

        //_db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.


        Log.w(TAG, "Upgrading from version " +_oldVersion + " to " +_newVersion + ", " +
                "which will destroy all old data");

        _db.execSQL(DROP_TABLE + TABLE_PERSONALBIO);
        _db.execSQL(DROP_TABLE + TABLE_HEALTHBIO);
        _db.execSQL(DROP_TABLE + TABLE_CATEGORY);
        _db.execSQL(DROP_TABLE + TABLE_MEDICINE);
        _db.execSQL(DROP_TABLE + TABLE_MEASUREMENT);
        _db.execSQL(DROP_TABLE + TABLE_CONSUMPTION);
        _db.execSQL(DROP_TABLE + TABLE_REMINDER);
        _db.execSQL(DROP_TABLE + TABLE_APPOINTMENT);
        _db.execSQL(DROP_TABLE + TABLE_ICE);

        onCreate(_db);
/*>>>>>>> eeec0a993cde1bf28bebd61b6f08e90393645978*/
    }

    @Override
    public void onOpen(SQLiteDatabase _db) {
        super.onOpen(_db);
    }
}