package com.example.armin.wave.standings.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public class TeamStandings implements IStandingsGeneric {
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("played")
    @Expose
    private String played;
    @SerializedName("win")
    @Expose
    private String win;
    @SerializedName("draw")
    @Expose
    private String draw;
    @SerializedName("loss")
    @Expose
    private String loss;
    @SerializedName("goals_for")
    @Expose
    private String goalsFor;
    @SerializedName("goals_against")
    @Expose
    private String goalsAgainst;
    @SerializedName("goal_diff")
    @Expose
    private String goalDiff;
    @SerializedName("points")
    @Expose
    private String points;

    public String getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPlayed() {
        return played;
    }

    public String getWin() {
        return win;
    }

    public String getDraw() {
        return draw;
    }

    public String getLoss() {
        return loss;
    }

    public String getGoalsFor() {
        return goalsFor;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public String getGoalDiff() {
        return goalDiff;
    }

    public String getPoints() {
        return points;
    }

    @Override
    public List<TeamStandings> GetSomething() {

        return null;
    }

    @Override
    public String GetTeamName() {
        return teamName;
    }

    @Override
    public String GetPoints() {
        return points;
    }

    @Override
    public String GetGet() {
        return null;
    }

    @Override
    public String GetRank() {
        return rank;
    }
}
