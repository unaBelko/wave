package com.example.armin.wave.utakmice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public class Match implements GenericList{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tournament_id")
    @Expose
    private String tournamentId;
    @SerializedName("matches")
    @Expose
    private List<Match_> matches = null;

    public String getName() {
        return name;
    }

    public List<Match_> getMatches() {
        return matches;
    }

    @Override
    public HashMap<String,String> GetName() {
        HashMap<String,String> name = new HashMap<>();
        name.put("country_name",this.name);
        name.put("tournament_id",tournamentId);
        return name;
    }

    @Override
    public String GetMatchIDDirty() {
        return null;
    }
}
