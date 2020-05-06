package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView totalGlobalConfirmed, totalGlobalRecovered, totalGlobalDeceased;
    private ProgressBar homeProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find button that take to statewise view
        Button inIndia = findViewById(R.id.button_In_India);

        inIndia.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent casesIntent = new Intent(MainActivity.this, StatewiseActivity.class);
                // Start the new activity
                startActivity(casesIntent);
            }
        });

        //main activity's text views to show global data
        totalGlobalConfirmed = findViewById(R.id.total_gobal_confirmed);
        totalGlobalRecovered = findViewById(R.id.total_gobal_recovered);
        totalGlobalDeceased = findViewById(R.id.total_gobal_deceased);

        //Main activity's progessBar
        homeProgressBar = findViewById(R.id.home_progress_bar);
        
        getData();

    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String globalURL = "https://api.covid19api.com/world/total";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, globalURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                homeProgressBar.setVisibility(View.GONE);
                try {
                    JSONObject globalJSONObject = new JSONObject(response);

                    totalGlobalConfirmed.setText(globalJSONObject.getString("TotalConfirmed"));
                    totalGlobalRecovered.setText(globalJSONObject.getString("TotalRecovered"));
                    totalGlobalDeceased.setText(globalJSONObject.getString("TotalDeaths"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                homeProgressBar.setVisibility(View.GONE);

                Log.d("Error In Response", error.toString());

            }
        });

        queue.add(stringRequest);
    }

    //this function is called by Coracle Ad TextView
    // open given link in browser


    public  void showAd(View v){
        final  String url = "http://www.coracle.in";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
