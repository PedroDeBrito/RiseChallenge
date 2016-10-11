package riseproject.pedrobrito.com.riseprojecttest.web;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import riseproject.pedrobrito.com.riseprojecttest.R;
import riseproject.pedrobrito.com.riseprojecttest.Structures.Movie;

public class WebOperations
{
    private Context context;

    public WebOperations(Context context)
    {
        this.context = context;
    }

    public void getJSON(String... urls)
    {
        new WebAsync().execute(urls);
    }

    private class WebAsync extends AsyncTask<String, Void, Movie>
    {
        private ImageView moviePosterView;
        private TextView titleTextView, yearTextView, runtimeTextView, genreTextView, plotTextView;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            titleTextView = (TextView) ((Activity) context).findViewById(R.id.title_info);
            yearTextView = (TextView) ((Activity) context).findViewById(R.id.year_info);
            runtimeTextView = (TextView) ((Activity) context).findViewById(R.id.runtime_info);
            genreTextView = (TextView) ((Activity) context).findViewById(R.id.genre_info);
            plotTextView = (TextView) ((Activity) context).findViewById(R.id.plot_info);
            moviePosterView = (ImageView) ((Activity) context).findViewById(R.id.movie_poster);
        }

        @Override
        protected Movie doInBackground(String... urls)
        {
            JSONObject jsonObject = getJSON(urls[0]);
            Movie movie = new Movie();
            try
            {
                movie.setTitle(getMovieInfo(jsonObject, "Title"));
                movie.setYear(getMovieInfo(jsonObject, "Year"));
                movie.setPlot(getMovieInfo(jsonObject, "Plot"));
                movie.setTime(getMovieInfo(jsonObject, "Runtime"));
                movie.setGenre(getMovieInfo(jsonObject, "Genre"));
                movie.setPoster(loadImage(jsonObject.getString("Poster")));
            }
            catch(Exception e)
            {
                Log.e("WebOperations", e.toString());
            }
            return movie;
        }

        @Override
        protected void onPostExecute(Movie movieInfo)
        {
            super.onPostExecute(movieInfo);

            titleTextView.setText(movieInfo.getTitle());
            yearTextView.setText(movieInfo.getYear());
            runtimeTextView.setText(movieInfo.getTime());
            genreTextView.setText(movieInfo.getGenre());
            plotTextView.setText(movieInfo.getPlot());
            moviePosterView.setImageBitmap(movieInfo.getPoster());
        }
    }

    private String getMovieInfo(JSONObject jsonObject, String name)
    {
        try
        {
            return jsonObject.getString(name);
        }
        catch(Exception e)
        {
            Log.e("WebOperations", e.toString());
            return "N/A";
        }
    }

    private Bitmap loadImage(String url)
    {
        try
        {
            return BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
        }
        catch (Exception e)
        {
            Log.e("WebOperations", "Could not retrieve movie poster!");
            return null;
        }
    }

    private JSONObject getJSON(String urlString)
    {

        JSONObject json = null;
        try
        {
            StringBuilder jsonString = new StringBuilder();

            HttpURLConnection connection = (HttpURLConnection)(new URL(urlString)).openConnection();
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                jsonString.append(line);
            }
            json = new JSONObject(jsonString.toString());

        }
        catch (Exception e)
        {
            Log.e("WebOperations", e.toString());
        }

        return json;
    }
}
