package com.example.movieexample;

import static com.example.movieexample.MainActivity.db_helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {

    EditText edtTitle, edtStudio, edtCRating;
    String title, studio, criticsRating, thumbnail;
    Button add;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        edtStudio = findViewById(R.id.editMovieStudio);
        edtTitle = findViewById(R.id.editMovieTitle);
        edtCRating = findViewById(R.id.editMovieCRating);
        add = findViewById(R.id.btnadd);

        if (edtStudio.toString().isEmpty() || edtTitle.toString().isEmpty() || edtCRating.toString().isEmpty()) {
            Toast.makeText(this, "Please enter value", Toast.LENGTH_SHORT).show();
        } else {
            title = edtTitle.getText().toString();
            studio = edtStudio.getText().toString();
            criticsRating = edtCRating.getText().toString();
            thumbnail = "";

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        title = edtTitle.getText().toString();
                        studio = edtStudio.getText().toString();
                        criticsRating = edtCRating.getText().toString();
                        thumbnail = "";
                        Log.e("mangodolly","title_insert   "+title);
                        db.insertData(title, studio, criticsRating, thumbnail);
                        Log.e("dalbhat", "data inserted");
                        Intent i=new Intent(EditActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    } catch (Exception e) {
                        Log.e("dalbhat", "exception_insertjion " + e.getMessage());
                    }
                }
            });
        }
    }
}