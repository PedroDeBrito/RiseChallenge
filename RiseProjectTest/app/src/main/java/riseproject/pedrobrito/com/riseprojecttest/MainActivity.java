package riseproject.pedrobrito.com.riseprojecttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import riseproject.pedrobrito.com.riseprojecttest.web.WebOperations;

public class MainActivity extends AppCompatActivity
{
    private WebOperations webOperations;
    private EditText titleEditText, yearEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        yearEditText = (EditText) findViewById(R.id.year_edit_text);

        webOperations = new WebOperations(this);
    }

    public void searchMovie(View v)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://www.omdbapi.com/?t=");
        stringBuilder.append(titleEditText.getText().toString().replaceAll("\\s+","+"));
        String yearString = yearEditText.getText().toString();
        stringBuilder.append("&y=");
        if(yearString.length() != 0)
            stringBuilder.append(yearString);
        stringBuilder.append("&plot=short");
        stringBuilder.append("&r=json");

        webOperations.getJSON(stringBuilder.toString());
    }
}
