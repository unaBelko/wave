package com.example.armin.wave.utakmice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public class UtakmiceResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("matches")
    @Expose
    private List<Match> matches = null;

    public Boolean getSuccess() {
        return success;
    }

    public List<Match> getMatches() {
        return matches;
    }
}
