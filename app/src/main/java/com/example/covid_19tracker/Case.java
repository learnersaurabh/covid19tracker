package com.example.covid_19tracker;

public class Case {

    private  String mStateName;
    private String mActiveCases;
    private  String mRecovered;
    private String mDeaths;


    public Case(String stateName,String active, String recovered, String deaths){
        mStateName = stateName;
        mActiveCases = active;
        mRecovered = recovered;
        mDeaths = deaths;
    }
    public String getActiveCases() {
        return mActiveCases;
    }
    public String getRecoveredCases(){
        return  mRecovered;
    }
    public String getDeaths(){
        return  mDeaths;
    }
    public String getStateName(){
        return  mStateName;
    }


}
