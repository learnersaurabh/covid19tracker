package com.example.covid_19tracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CaseAdapter extends ArrayAdapter<Case> {

    CaseAdapter(Activity context, ArrayList<Case> cases) {
        super(context, 0, cases);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.states_list, parent, false);
        }

        Case currentCase = getItem(position);

        TextView stateTextView = (TextView) listItemView.findViewById(R.id.state_name_view);
        stateTextView.setText(currentCase.getStateName());

        TextView activeTextView = (TextView) listItemView.findViewById(R.id.active_case_view);
        activeTextView.setText(currentCase.getActiveCases());

        TextView recoveredTextView = (TextView) listItemView.findViewById(R.id.recovered_case_view);
        recoveredTextView.setText(currentCase.getRecoveredCases());

        TextView deathTextView = (TextView) listItemView.findViewById(R.id.death_case_view);
        deathTextView.setText(currentCase.getDeaths());

        return listItemView;
    }
}