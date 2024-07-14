package com.example.movieexample;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class AllMoviesAdapter extends RecyclerView.Adapter<AllMoviesAdapter.ViewHolder>
{

    public Context context;
    public List<MovieMode> movie_list;
    OnCLicked onCLicked;




    public AllMoviesAdapter(Context context, List<MovieMode> movie_list,OnCLicked onCLicked)
    {
        this.context = context;
        this.movie_list = movie_list;
        this.onCLicked=onCLicked;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        ViewHolder card = new ViewHolder(v);
        return card;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position)
    {


        final MovieMode movie_details = movie_list.get(position);

//        int id=movie_details.id;
        String title=movie_details.title;
        String studio=movie_details.studio;
        String cric_rating=movie_details.criticsRating;
        String imagepath=movie_details.thumbnail;
        Log.e("mango","imagepath"+imagepath);

        holder.movieTitle.setSelected(true);
        holder.movieTitle.setText(title);
        holder.movieCRating.setText(cric_rating);
        holder.movieStudio.setText(studio);
        Glide.with(context).load(Uri.parse(imagepath)).into(holder.movieImage);


        holder.btnMEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCLicked.on_clicked(movie_details);
               // on_movie_clicked.on_edit_clicked(id,movie_details);


            }
        });

        holder.btnMDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on_movie_clicked.on_delete_clicked(id,movie_details);
                onCLicked.on_delete_clicked(movie_details);
            }
        });

    }



    @Override
    public int getItemCount() {

        return movie_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle,movieStudio,movieCRating;
       ImageView movieImage;
       Button btnMEdit,btnMDelete;

       LinearLayout fl_category;

        public ViewHolder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieImage);
            btnMEdit = itemView.findViewById(R.id.btnMEdit);
            btnMDelete = itemView.findViewById(R.id.btnMDelete);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieStudio = itemView.findViewById(R.id.movieStudio);
            movieCRating = itemView.findViewById(R.id.movieCRating);


        }
    }

}
