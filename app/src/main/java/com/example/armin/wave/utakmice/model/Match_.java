package com.example.armin.wave.utakmice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Una on 28.10.2017..
 */

public class Match_ implements GenericList{

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("home_team")
    @Expose
    private String homeTeam;
    @SerializedName("away_team")
    @Expose
    private String awayTeam;
    @SerializedName("match_id")
    @Expose
    private String matchId;
    @SerializedName("tournament_id")
    @Expose
    private String tournamentId;
    @SerializedName("home_score")
    @Expose
    private String homeScore = null;
    @SerializedName("away_score")
    @Expose
    private String awayScore = null;
    @SerializedName("home_win")
    @Expose
    private String homeWin = null;
    @SerializedName("away_win")
    @Expose
    private String awayWin = null;
    @SerializedName("draw")
    @Expose
    private String draw = null;

    public String getTime() {
        return time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getTournamentId() {
        return tournamentId;
    }



    @Override
    public HashMap<String,String> GetName() {
        HashMap<String,String> names = new HashMap<>();
        names.put("time",time);
        names.put("home_team",homeTeam);
        names.put("away_team",awayTeam);
        names.put("match_id",matchId);
        names.put("tournament_id",tournamentId);
        names.put("home_win",homeWin);
        names.put("away_win",awayWin);
        names.put("draw",draw);
        if(homeScore != null && awayScore != null){
            names.put("home_score",homeScore);
            names.put("away_score",awayScore);
        }
        return names;
    }

    @Override
    public String GetMatchIDDirty() {
        return matchId;
    }
}
