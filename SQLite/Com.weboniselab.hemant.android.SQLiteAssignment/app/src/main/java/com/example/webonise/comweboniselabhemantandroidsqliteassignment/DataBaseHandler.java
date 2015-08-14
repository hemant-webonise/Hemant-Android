package com.example.webonise.comweboniselabhemantandroidsqliteassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String COLUMN_ID ="ID";
    private static final String COLUMN_NAME ="Name";
    private static final String COLUMN_AGE ="Age" ;
    private static final String COLUMN_HEIGHT = "HT";
    private static final String COLUMN_WEIGHT = "WT";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DETAILS_TABLE = "CREATE TABLE " + TABLE_DETAILS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTO-INCREMENT, "
                + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " INTEGER ," + COLUMN_WEIGHT + " INTEGER, "
                + COLUMN_HEIGHT + " INTEGER ) ";
        db.execSQL(CREATE_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }


    public void addDetails(Details details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, details.getDetailsName());
        values.put(COLUMN_AGE, details.getDetailsAge());
        values.put(COLUMN_HEIGHT, details.getDetailsHt());
        values.put(COLUMN_WEIGHT, details.getDetailsWt());
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
                        details.setDetailsId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID))));
                        details.setDetailsName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                        details.setDetailsAge(Integer.parseInt(cursor.getString(2)));
                        details.setDetailsHt(Integer.parseInt(cursor.getString(3)));
                        details.setDetailsWt(Integer.parseInt(cursor.getString(4)));
                        // Adding contact to list
                        DetailList.add(details);
                    } while (cursor.moveToNext());
                }
                // return contact list
                cursor.close();
                db.close();
                return DetailList;
            }   catch (Exception e) {

            }
        return DetailList;
    }


    public int updateDetails(Details details){ return 1;}

}
