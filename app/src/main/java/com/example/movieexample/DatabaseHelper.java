package com.example.movieexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "com.example.yash_jain_numbersgame.Movies.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_STUDIO= "studio";
//    public static final String COLUMN_GENRES= "genres";
//    public static final String COLUMN_DIRECTORS= "directors";
//    public static final String COLUMN_WRITERS= "writers";
//    public static final String COLUMN_ACTORS= "actors";
//    public static final String COLUMN_YEARS= "year";
//    public static final String COLUMN_LENGTH= "length";
//    public static final String COLUMN_SHORTDESCRIPTION= "shortDescription";
//    public static final String COLUMN_MAPRATING= "mpaRating";
    public static final String COLUMN_CRITICSRATING= "criticsRating";
    public static final String COLUMN_IMAGE= "image";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MOVIES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_STUDIO + " TEXT, " +
//                COLUMN_GENRES + " TEXT, " +
//                COLUMN_DIRECTORS + " TEXT, " +
//                COLUMN_WRITERS + " TEXT, " +
//                COLUMN_ACTORS + " TEXT, " +
//                COLUMN_YEARS + " TEXT, " +
//                COLUMN_LENGTH + " TEXT, " +
//                COLUMN_SHORTDESCRIPTION + " TEXT, " +
//                COLUMN_MAPRATING + " TEXT, " +
                COLUMN_CRITICSRATING + " TEXT, " +
                COLUMN_IMAGE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public void insertData(String title, String studio,
//                           String genres, String directors, String writers, String actors,String years,
//                             String length, String short_desc, String mparate,
                           String cric_rate, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_STUDIO, studio);
//        values.put(COLUMN_GENRES, genres);
//        values.put(COLUMN_DIRECTORS, directors);
//        values.put(COLUMN_WRITERS, writers);
//        values.put(COLUMN_ACTORS, actors);
//        values.put(COLUMN_YEARS, years);
//        values.put(COLUMN_LENGTH, length);
//        values.put(COLUMN_SHORTDESCRIPTION, short_desc);
//        values.put(COLUMN_MAPRATING, mparate);
        values.put(COLUMN_CRITICSRATING, cric_rate);
        values.put(COLUMN_IMAGE, image);
        db.insert(TABLE_MOVIES, null, values);
    }

    public void deleteData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_MOVIES + " WHERE " + COLUMN_TITLE + "='" + title + "';");

           // db.execSQL("DELETE FROM " + TABLE_MOVIES + " WHERE " + COLUMN_ID + "=" + id + ";");
        } finally {
            db.close();
        }
    }
}
