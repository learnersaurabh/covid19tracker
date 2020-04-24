package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class StatewiseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_list);

        final ArrayList<Case> cases = new ArrayList<>();

        CaseAdapter adapter;

        ListView listView = findViewById(R.id.list);

        cases.add(new Case("Uttar pradesh","2034","564","68"));
        cases.add(new Case("kashmir","134","54","8"));
        cases.add(new Case("Delhi","2234","560","60"));
        cases.add(new Case("Mumbai","4134","1264","98"));
        cases.add(new Case("Kolkata","1734","864","58"));
        cases.add(new Case("Telangana","124","64","6"));
        cases.add(new Case("Rajisthan","5234","1364","268"));
        cases.add(new Case("Punjab","234","74","18"));
        cases.add(new Case("Haryana","6234","2004","38"));
        cases.add(new Case("Uttar pradesh","2034","564","68"));
        cases.add(new Case("kashmir","134","54","8"));
        cases.add(new Case("Delhi","2234","560","60"));
        cases.add(new Case("Mumbai","4134","1264","98"));
        cases.add(new Case("Kolkata","1734","864","58"));
        cases.add(new Case("Telangana","124","64","6"));
        cases.add(new Case("Rajisthan","5234","1364","268"));
        cases.add(new Case("Punjab","234","74","18"));
        cases.add(new Case("Haryana","6234","2004","38"));


        listView.setAdapter(new CaseAdapter(this, cases));

    }
}
