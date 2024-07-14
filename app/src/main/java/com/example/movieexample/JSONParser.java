package com.example.movieexample;
import static com.example.movieexample.MainActivity.db_helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

    public void parseJSON(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Example JSON object fields
               // int id = jsonObject.getInt("id");
                String title = jsonObject.getString("title");
                String studio = jsonObject.getString("studio");
                String criticsRating = jsonObject.getString("criticsRating");
                String thumbnail = jsonObject.getString("image");
//                int criticsRating = jsonObject.getInt("criticsRating");
                Log.e("dalbhat","data_initialized");


                try
                {
                    db_helper.insertData(title, studio,criticsRating,thumbnail);
                    Log.e("dalbhat","data inserted");

                }
                catch (Exception e)
                {
                    Log.e("dalbhat","exception_insertjion "+e.getMessage());
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
