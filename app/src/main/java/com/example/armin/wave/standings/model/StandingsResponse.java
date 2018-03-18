package com.example.armin.wave.standings.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public class StandingsResponse implements IStandingsGeneric {

    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("teams")
    @Expose
    private List<TeamStandings> teams = null;

    public String getGroup() {
        return group;
    }

    public List<TeamStandings> getTeams() {
        return teams;
    }

    @Override
    public List<TeamStandings> GetSomething() {
        return teams;
    }

    @Override
    public String GetTeamName() {
        return null;
    }

    @Override
    public String GetPoints() {
        return null;
    }

    @Override
    public String GetGet() {
        return group;
    }

    @Override
    public String GetRank() {
        return null;
    }
}
