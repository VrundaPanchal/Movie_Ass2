package com.example.movieexample;

public class MovieMode
{
    public int id;
    public String title;
    public String studio;
    public String thumbnail;
    public String criticsRating;


    // Constructor
    public MovieMode( String title, String studio, String thumbnail, String criticsRating) {

        this.title = title;
        this.studio = studio;
        this.thumbnail = thumbnail;
        this.criticsRating = criticsRating;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getCriticsRating() {
        return criticsRating;
    }

    public void setCriticsRating(String criticsRating) {
        this.criticsRating = criticsRating;
    }



}
