package com.example.hemant.listviewfinals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DetailsDBAdapter extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DATABASE_NAME = "Persons";
    public static final String TABLE_DETAILS = "Details";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_WEIGHT = "Weight";
    public static final String COLUMN_HEIGHT = "Height";
    public static final int ID = 0;

    public DetailsDBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_DETAILS + " ( " + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, " + COLUMN_AGE + " TEXT ," + COLUMN_HEIGHT + " TEXT , "
                + COLUMN_WEIGHT + " TEXT ) ";
         sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        onCreate(db);
    }

    public void createDetails(Details personDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        /*ContentValues Approach*/
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_NAME, personDetails.getName());
        initialValues.put(COLUMN_AGE, personDetails.getAge());
        initialValues.put(COLUMN_HEIGHT, personDetails.getHeight());
        initialValues.put(COLUMN_WEIGHT, personDetails.getWeight());
        if(db!=null && db.isOpen()) {
            db.insert(TABLE_DETAILS, null, initialValues);
        }
        db.close();
    }

    public void deleteCertainDetail(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DETAILS, COLUMN_ID + "=" + id, null);
        db.close();
    }


    public List<Details> fetchAllDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Details> personDetailsList = new ArrayList<Details>();

        String selectCount = "SELECT COUNT(*) FROM " + TABLE_DETAILS;
        Cursor cursor = db.rawQuery(selectCount, null);
        cursor.moveToFirst();
        if (cursor.getInt(ID) > ID) {
            String selectAll = "SELECT * FROM " + TABLE_DETAILS;
            cursor = db.rawQuery(selectAll, null);
            cursor.moveToFirst();
            do {
                Details personDetails = new Details();
                personDetails.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                personDetails.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                personDetails.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                personDetails.setHeight(cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT)));
                personDetails.setWeight(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
                personDetailsList.add(personDetails);
            }
            while (cursor.moveToNext());
        } else {
            Details personDetails = new Details();
            personDetails.setName(Constants.EMPTY);
            personDetailsList.add(personDetails);
        }
        return personDetailsList;
    }


}
