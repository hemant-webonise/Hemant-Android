package com.example.webonise.FragmentAssignment;

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
    /*Will put values in DB from editText*/
    public void createDetails(Details details) {
        SQLiteDatabase db = this.getWritableDatabase();
        /*ContentValues Approach*/
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_NAME, details.getName());
        initialValues.put(COLUMN_AGE, details.getAge());
        initialValues.put(COLUMN_HEIGHT, details.getHeight());
        initialValues.put(COLUMN_WEIGHT, details.getWeight());
        if(db!=null && db.isOpen()) {
            db.insert(TABLE_DETAILS, null, initialValues);
        }
        db.close();
    }
    /*Will return a list-item on ID */
    public void deleteCertainDetail(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DETAILS, COLUMN_ID + "=" + id, null);
        db.close();
    }

    /*Will return a list */
    public List<Details> fetchAllDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Details> detailsList = new ArrayList<Details>();

        String selectCount = "SELECT COUNT(*) FROM " + TABLE_DETAILS;
        Cursor cursor = db.rawQuery(selectCount, null);
        cursor.moveToFirst();
        if (cursor.getInt(ID) > ID) {
            String selectAll = "SELECT * FROM " + TABLE_DETAILS;
            cursor = db.rawQuery(selectAll, null);
            cursor.moveToFirst();
            do {
                Details details = new Details();
                details.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                details.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                details.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_AGE)));
                details.setHeight(cursor.getString(cursor.getColumnIndex(COLUMN_HEIGHT)));
                details.setWeight(cursor.getString(cursor.getColumnIndex(COLUMN_WEIGHT)));
                detailsList.add(details);
            }
            while (cursor.moveToNext());
        } else {
            Details details = new Details();
            details.setName(Constants.EMPTY);
            detailsList.add(details);
        }
        return detailsList;
    }
}
