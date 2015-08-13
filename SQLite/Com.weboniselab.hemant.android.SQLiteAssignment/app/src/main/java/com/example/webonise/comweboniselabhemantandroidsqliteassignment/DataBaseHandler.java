package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by webonise on 13/8/15.
 */
public class DataBaseHandler extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DetailsManager";
    // Details table name
    private static final String TABLE_DETAILS = "Details";
    // Details Table Columns names
    private static final String KEY_ID ="ID";
    private static final String KEY_NAME ="Name";
    private static final String KEY_AGE ="Age" ;
    private static final String KEY_HT = "HT";
    private static final String KEY_WT = "WT";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DETAILS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTO-INCREMENT, "
                + KEY_NAME + " TEXT, " + KEY_AGE + " INTEGER ," + KEY_WT + " INTEGER, "
                + KEY_HT + " INTEGER ) ";
        db.execSQL(CREATE_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        // Create tables again
        onCreate(db);
    }

    public void addDetails(Details details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, details.getDetails_name());
        values.put(KEY_AGE, details.getDetails_age());
        values.put(KEY_HT, details.getDetails_ht());
        values.put(KEY_WT, details.getDetails_wt());
        // Inserting Row
        db.insert(TABLE_DETAILS, null, values);
        db.close(); // Closing database connection

    }
    public void getDetails(){
        SQLiteDatabase db = this.getReadableDatabase();

    }


    public List<Details> getAllDetails(){
        List<Details> DetailList = new ArrayList<Details>();
            try {

                // Select All Query
                String selectQuery = "SELECT * FROM " + TABLE_DETAILS;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);

                 // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Details details = new Details();
                        details.setDetails_id(Integer.parseInt(cursor.getString(0)));
                        details.setDetails_name(cursor.getString(1));
                        details.setDetails_age(Integer.parseInt(cursor.getString(2)));
                        details.setDetails_ht(Integer.parseInt(cursor.getString(3)));
                        details.setDetails_wt(Integer.parseInt(cursor.getString(4)));
                        // Adding contact to list
                        DetailList.add(details);
                    } while (cursor.moveToNext());
                }

                // return contact list
                cursor.close();
                db.close();
                return DetailList;
            }   catch (Exception e) {
                // TODO: handle exception
                Log.e("all_Details", "" + e);
            }
        return DetailList;
    }


    public int updateDetails(Details details){ return 1;}

}
