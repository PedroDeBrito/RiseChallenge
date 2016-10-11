package riseproject.pedrobrito.com.riseprojecttest.Structures;

import android.graphics.Bitmap;

public class Movie
{
    private String title, year, plot, time, genre;
    private Bitmap poster;

    public Movie()
    {

    }

    public Movie(String title, String year, String plot, String time, String genre, Bitmap poster)
    {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.time = time;
        this.genre = genre;
        this.poster = poster;
    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
