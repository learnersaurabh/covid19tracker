package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

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

    ProgressBar progressBar;

    ListView listView = findViewById(R.id.list);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_list);

        final ArrayList<Case> cases = new ArrayList<>();

        getData();

        listView.setAdapter(new CaseAdapter(this, cases));

    }
    private void getData() {

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.covid19india.org/data.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);

                try {

                    final ArrayList<Case> cases = new ArrayList<>();

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


                    listView.setAdapter(new CaseAdapter(StatewiseActivity.this, cases));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);

                Log.d("Error In Response", error.toString());

            }
        });

        queue.add(request);

    }
}

