package com.example.webonise.listviewandsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

/**
 * Created by webonise on 13/8/15.
 */
public class DetailsDBAdapter {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DetailsManager";
    // Details table name
    private static final String TABLE_DETAILS = "Details";

    public static final String KEY_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_HEIGHT = "Height";
    public static final String COLUMN_WEIGHT = "Weight";

    private static final String TAG = "DetailsDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private final Context mCtx;

    private static final String DATABASE_CREATE = "CREATE TABLE  IF NOT EXISTS  " + TABLE_DETAILS + " ( " + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " TEXT ," + COLUMN_HEIGHT + " TEXT , "
                +COLUMN_WEIGHT + " TEXT ) ;";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
            onCreate(db);
        }

    }
    public void createDetails(String name, String age, String height, String weight) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_NAME, name);
        initialValues.put(COLUMN_AGE, age);
        initialValues.put(COLUMN_HEIGHT, height);
        initialValues.put(COLUMN_WEIGHT, weight);
        if(mDb!=null && mDb.isOpen()) {
            mDb.insert(TABLE_DETAILS, null, initialValues);
        }
    }

    public DetailsDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }


    public DetailsDBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }
    public void deleteCertainDetail(int id){
        mDb.delete(TABLE_DETAILS, KEY_ID + "=" + id, null);
    }

    public boolean deleteAllDetails() {

        int doneDelete = 0;
        doneDelete = mDb.delete(TABLE_DETAILS, null , null);
        return doneDelete > 0;
    }

    public Cursor fetchDetailsByName(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(TABLE_DETAILS, new String[] {KEY_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_HEIGHT, COLUMN_WEIGHT}, null, null, null, null, null);
        }
        else { mCursor = mDb.query(true, TABLE_DETAILS, new String[] {KEY_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_HEIGHT, COLUMN_WEIGHT},
                    COLUMN_AGE + " like '%" + inputText + "%'", null, null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    public Cursor fetchAllDetails() {

        Cursor mCursor = mDb.query(TABLE_DETAILS, new String[] {KEY_ID,
                        COLUMN_NAME, COLUMN_AGE, COLUMN_HEIGHT, COLUMN_WEIGHT},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


}
