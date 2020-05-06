package com.example.covid_19tracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StatewiseActivity extends AppCompatActivity {

//    ProgressBar progressBar;
    ListView listView;
    final ArrayList<Case> cases = new ArrayList<>();
    boolean progress = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_list);

        listView = findViewById(R.id.list);

        final ArrayList<Case> cases = new ArrayList<>();

        getData();

    }
    private void getData() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.covid19india.org/data.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

//                progressBar.setVisibility(View.GONE);

                try {


                    JSONObject root = new JSONObject(response);
                    JSONArray statewiseJSONArray = root.getJSONArray("statewise");
                    for (int i = 1; i < statewiseJSONArray.length(); i++) {
                        JSONObject subJSONObject = statewiseJSONArray.getJSONObject(i);

                        String stateName = subJSONObject.getString("state");
                        String stateConfirmed = subJSONObject.getString("confirmed");
                        String stateRecovered = subJSONObject.getString("recovered");
                        String stateDeaths= subJSONObject.getString("deaths");

                        cases.add(new Case(stateName,stateConfirmed,stateRecovered,stateDeaths));
                    }


                    listView.setAdapter(new CaseAdapter(progress,StatewiseActivity.this, cases));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

//                progressBar.setVisibility(View.GONE);
                listView.setAdapter(new CaseAdapter(progress,StatewiseActivity.this, cases));


                Log.d("Error In Response", error.toString());

            }
        });

        queue.add(request);

    }
}

