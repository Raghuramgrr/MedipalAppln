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

    public static final String TABLE_PERSONALBIO= "PersonalBio";
    public static final String TABLE_HEALTHBIO= "HealthBio";
    public static final String TABLE_CATEGORIES= "Categories";
    public static final String TABLE_MEDICINE= "Medicine";
    public static final String TABLE_MEASUREMENTS= "Measurements";
    public static final String TABLE_CONSUMPTION= "Consumption";
    public static final String TABLE_REMINDERS= "Reminders";
    public static final String TABLE_APPOINTMENT= "Appointment";
    public static final String TABLE_ICE= "ICE";

    //table columns
    public static final String COLUMN_PERSONALBIO[] = {"ID", "Name", "DOB", "IDNo", "Address",
            "PostalCode", "Height", "BloodType"};

    public static final String COLUMN_HEALTHBIO[] = {"ID", "Condition", "StartDate", "ConditionType"};

    public static final String COLUMN_CATEGORIES[] = {"ID", "Category", "Code", "Description",
            "Remind"};

    public static final String COLUMN_MEDICINE[] = {"ID", "Medicine", "Description", "CatID",
            "ReminderID", "Remind", "Quantity", "Dosage", "ConsumeQualty", "Threshold", "DateIssued",
            "ExpireFactor"};

    public static final String COLUMN_MEASUREMENTS[] = {"ID", "Systolic", "Diastolic", "Pulse",
            "Temperature", "Weight ", "MeasuredOn"};

    public static final String COLUMN_CONSUMPTION[] = {"ID", "MedicineID", "Quantity", "ConsumedOn"};

    public static final String COLUMN_REMINDERS[] = {"ID", "Frequency", "StartTime", "Interval"};

    public static final String COLUMN_APPOINTMENT[] = {"ID", "Location", "Appointment", "Description"};
    public static final String COLUMN_ICE[] = {"ID", "Name", "ContactNo", "ContactType", "Description",
            "Sequence"};

    //begin SQL statements
    public static final String CREATE_TABLE_PERSONALBIO = "CREATE TABLE " + TABLE_PERSONALBIO +
            "(" +
            COLUMN_PERSONALBIO[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_PERSONALBIO[1] + " VARCHAR(100) " +
            COLUMN_PERSONALBIO[2] + " DATE " +
            COLUMN_PERSONALBIO[3] + " VARCHAR(20) " +
            COLUMN_PERSONALBIO[4] + " VARCHAR(100) " +
            COLUMN_PERSONALBIO[5] + " VARCHAR(10) " +
            COLUMN_PERSONALBIO[6] + " INTEGER " +
            COLUMN_PERSONALBIO[7] + " VARCHAR(10) " +
            ");";

    public static final String CREATE_TABLE_HEALTHBIO = "CREATE TABLE " + TABLE_HEALTHBIO +
            "(" +
            COLUMN_HEALTHBIO[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_HEALTHBIO[1] + " VARCHAR(255) " +
            COLUMN_HEALTHBIO[2] + " DATE " +
            COLUMN_HEALTHBIO[3] + " VARCHAR(1) " +
            ");";

    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES +
            "(" +
            COLUMN_CATEGORIES[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_CATEGORIES[1] + " VARCHAR(50) " +
            COLUMN_CATEGORIES[2] + " VARCHAR(5) " +
            COLUMN_CATEGORIES[3] + " VARCHAR(255) " +
            COLUMN_CATEGORIES[4] + " INTEGER DEFAULT 0" + //there is no boolean type in sqlite
            ");";

    public static final String CREATE_TABLE_MEDICINE = "CREATE TABLE " + TABLE_MEDICINE +
            "(" +
            COLUMN_MEDICINE[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_MEDICINE[1] + " VARCHAR(50) " +
            COLUMN_MEDICINE[2] + " VARCHAR(255) " +
            COLUMN_MEDICINE[3] + " INTEGER " +
            COLUMN_MEDICINE[4] + " INTEGER" +
            COLUMN_MEDICINE[5] + " INTEGER DEFAULT 0" +
            COLUMN_MEDICINE[6] + " INTEGER" +
            COLUMN_MEDICINE[7] + " INTEGER" +
            COLUMN_MEDICINE[8] + " INTEGER" +
            COLUMN_MEDICINE[9] + " INTEGER" +
            COLUMN_MEDICINE[10] + " DATE" +
            COLUMN_MEDICINE[11] + " INTEGER" +
            ");";

    public static final String CREATE_TABLE_MEASUREMENTS = "CREATE TABLE " + TABLE_MEASUREMENTS +
            "(" +
            COLUMN_MEASUREMENTS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_MEASUREMENTS[1] + " INTEGER " +
            COLUMN_MEASUREMENTS[2] + " INTEGER " +
            COLUMN_MEASUREMENTS[3] + " INTEGER " +
            COLUMN_MEASUREMENTS[4] + " DECIMAL(5,2)" +
            COLUMN_MEASUREMENTS[5] + " INTEGER" +
            COLUMN_MEASUREMENTS[6] + " DATETIME" +
            ");";

    public static final String CREATE_TABLE_CONSUMPTION = "CREATE TABLE " + TABLE_CONSUMPTION +
            "(" +
            COLUMN_CONSUMPTION[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_CONSUMPTION[1] + " INTEGER " +
            COLUMN_CONSUMPTION[2] + " INTEGER " +
            COLUMN_CONSUMPTION[3] + " DATETIME " +
            ");";

    public static final String CREATE_TABLE_REMINDERS = "CREATE TABLE " + TABLE_REMINDERS +
            "(" +
            COLUMN_REMINDERS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_REMINDERS[1] + " INTEGER " +
            COLUMN_REMINDERS[2] + " DATETIME " +
            COLUMN_REMINDERS[3] + " INTEGER " +
            ");";

    public static final String CREATE_TABLE_APPOINTMENT = "CREATE TABLE " + TABLE_APPOINTMENT +
            "(" +
            COLUMN_APPOINTMENT[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_APPOINTMENT[1] + " VARCHAR(100) " +
            COLUMN_APPOINTMENT[2] + " DATETIME " +
            COLUMN_APPOINTMENT[3] + " VARCHAR(255) " +
            ");";

    public static final String CREATE_TABLE_ICE = "CREATE TABLE " + TABLE_ICE +
            "(" +
            COLUMN_ICE[0] + " INTEGER PRIMARY KEY AUTOINCREMENT " +
            COLUMN_ICE[1] + " VARCHAR(100) " +
            COLUMN_ICE[2] + " VARCHAR(20) " +
            COLUMN_ICE[3] + " INTEGER " +
            COLUMN_ICE[4] + " VARCHAR(255) " +
            COLUMN_ICE[5] + " INTEGER " +
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

        _db.execSQL(CREATE_TABLE_PERSONALBIO);
        _db.execSQL(CREATE_TABLE_HEALTHBIO);
        _db.execSQL(CREATE_TABLE_CATEGORIES);
        _db.execSQL(CREATE_TABLE_MEDICINE);
        _db.execSQL(CREATE_TABLE_MEASUREMENTS);
        _db.execSQL(CREATE_TABLE_CONSUMPTION);
        _db.execSQL(CREATE_TABLE_REMINDERS);
        _db.execSQL(CREATE_TABLE_APPOINTMENT);
        _db.execSQL(CREATE_TABLE_ICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w(TAG, "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        _db.execSQL(DROP_TABLE + TABLE_PERSONALBIO);
        _db.execSQL(DROP_TABLE + TABLE_HEALTHBIO);
        _db.execSQL(DROP_TABLE + TABLE_CATEGORIES);
        _db.execSQL(DROP_TABLE + TABLE_MEDICINE);
        _db.execSQL(DROP_TABLE + TABLE_MEASUREMENTS);
        _db.execSQL(DROP_TABLE + TABLE_CONSUMPTION);
        _db.execSQL(DROP_TABLE + TABLE_REMINDERS);
        _db.execSQL(DROP_TABLE + TABLE_APPOINTMENT);
        _db.execSQL(DROP_TABLE + TABLE_ICE);

        onCreate(_db);
    }

    @Override
    public void onOpen(SQLiteDatabase _db) {
        super.onOpen(_db);
    }
}