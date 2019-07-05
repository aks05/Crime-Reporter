package com.example.adsadf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class CrimeDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "crime_database";
    private static final String TABLE_NAME = "crime_table";
    private static final String COL2 = "category";
    private static final String COL3 = "month";
    private static final String COL4 = "location_type";
    private static final String COL5 = "location_subtype";
    private static final String COL6 = "latitude";
    private static final String COL7 = "longitude";
    private static final String COL8 = "street";
    private static final String COL9 = "context";
    private static final String COL10 = "outcome_status";
    private static final String COL11 = "last_dateof_action";


    CrimeDatabase(Context context) {
        super(context, DATABASE_NAME, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT,"+COL3+" TEXT, "+COL4+" TEXT, "+COL5+" TEXT, "+COL6+ " TEXT, "+COL7+
                " TEXT, "+COL8+" TEXT, "+ COL9+" TEXT, "+COL10+" TEXT, "+COL11+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL( String.format( " DROP TABLE IF EXISTS %s", TABLE_NAME ) );
        onCreate(db);
    }

    boolean addData(String[] item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item[0]);
        contentValues.put(COL3,item[1]);
        contentValues.put(COL4, item[2]);
        contentValues.put(COL5, item[3]);
        contentValues.put(COL6, item[4]);
        contentValues.put(COL7, item[5]);
        contentValues.put(COL8, item[6]);
        contentValues.put(COL9, item[7]);
        contentValues.put(COL10, item[8]);
        contentValues.put(COL11, item[9]);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result >0;
    }

    Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    void deleteData(String[] item) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL2 + " = '" + item[0] + "' AND "+ COL3 +" = '"+ item[1] + "' AND "
                + COL4 + " = '" + item[2] + "' AND "+ COL5 +" = '"+ item[3] + "' AND "
                + COL6 + " = '" + item[4] + "' AND "+ COL7 +" = '"+ item[5] + "' AND "
                + COL8 + " = '" + item[6] + "' AND "+ COL9 +" = '"+ item[7] + "' AND "
                + COL10 + " = '" + item[8] + "' AND "+ COL11 +" = '"+ item[9] + "' ";
        db.execSQL(query);
    }
}

