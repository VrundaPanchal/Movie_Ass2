package com.example.movieexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
    private Context context;

    public DataFetcher(Context context) {
        this.context = context;
    }

    public List<MovieMode> fetchAllMovies()
    {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<MovieMode> movieList = new ArrayList<>();

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_MOVIES,
                null, null, null, null, null, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                   // int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
                    String studio = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_STUDIO));
                    String thumbnail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE));
                    String criticsRating = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CRITICSRATING));

                    MovieMode movie = new MovieMode(title, studio, thumbnail, criticsRating);
                    movieList.add(movie);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
            db.close();
        }

        return movieList;
    }
}