package com.example.movieexample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    RecyclerView rvMovieList;
    FloatingActionButton fabAdd;
    public static DatabaseHelper db_helper;
    public AllMoviesAdapter all_moviewadapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("vrunda_data", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Insert the data into the database
         db_helper = new DatabaseHelper(MainActivity.this);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        rvMovieList = findViewById(R.id.moviesList);
        fabAdd=findViewById(R.id.fabadd);

        refresh();



        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                startActivity(i);
            }
        });
    }

    OnCLicked onCLicked=new OnCLicked() {
        @Override
        public void on_clicked(MovieMode moviewmodel)
        {
           Intent i=new Intent(MainActivity.this,EditActivity.class);
           startActivity(i);
        }

        @Override
        public void on_delete_clicked(MovieMode position) {
            Log.e("mangodolly","title "+position.title);

            db_helper.deleteData(position.title);
            refresh();
            //refresh();
        }
    };

    public void refresh(){
        boolean dataadded = sharedPreferences.getBoolean("dataadded", false);

        if (dataadded) {
            Log.e("mango", "data_already_loaded ");
        } else {
            String jsonStr = loadJSONFromAsset();
            JSONParser jsonparser=new JSONParser();
            if (editor != null) {
                editor.putBoolean("dataadded", true);
                editor.apply();
            }
            jsonparser.parseJSON(jsonStr);
            Log.e("mango", "data_inserted ");
        }

        Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                DataFetcher dataFetcher = new DataFetcher(MainActivity.this);
                List<MovieMode> movies = dataFetcher.fetchAllMovies();

                if (movies!=null && movies.size()>0)
                {
                    LinearLayoutManager gridLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
                    rvMovieList.setLayoutManager(gridLayoutManager);

                    all_moviewadapter = new AllMoviesAdapter(MainActivity.this, movies,onCLicked);
                    rvMovieList.setAdapter(all_moviewadapter);
                }

//                // Do something with the fetched data (e.g., display in a RecyclerView)
//                for (MovieMode movie : movies)
//                {
//                    Log.e("dalbhat","title "+movie.title);
//                   // System.out.println("Title: " + movie.getTitle());
//                    // Other fields
//                }
            }
        });

    }
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("db_movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
