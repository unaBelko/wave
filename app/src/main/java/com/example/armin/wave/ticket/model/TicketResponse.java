package com.example.armin.wave.ticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Una on 29.10.2017..
 */

public class TicketResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("bets")
    @Expose
    private List<Bet> bets = null;

    public Boolean getSuccess() {
        return success;
    }

    public List<Bet> getBets() {
        return bets;
    }
}
