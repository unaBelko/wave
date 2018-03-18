package com.example.armin.wave.ticket.model;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Una on 29.10.2017..
 */

public class Item implements ITicketGeneric {
    @SerializedName("matchID")
    @Expose
    private String matchID;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("morethan2")
    @Expose
    private Object morethan2;
    @SerializedName("home_name")
    @Expose
    private String homeName;
    @SerializedName("away_name")
    @Expose
    private String awayName;
    @SerializedName("match_odds")
    @Expose
    private float matchOdds;

    public String getMatchID() {
        return matchID;
    }

    public String getResult() {
        return result;
    }

    public Object getMorethan2() {
        return morethan2;
    }

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public float getMatchOdds() {
        return matchOdds;
    }

    @Override
    public HashMap<String, String> GetNesto() {
        HashMap<String,String> map = new HashMap<>();
        map.put("result",result);
        map.put("morethan2", (String) morethan2);
        map.put("home",homeName);
        map.put("away",awayName);
        map.put("match_odds", String.valueOf(matchOdds));

        return map;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public int totalOdd() {
        return 0;
    }

    @Override
    public String winState() {
        return "";
    }
}
