package com.example.armin.wave.ticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 29.10.2017..
 */

public class Bet implements ITicketGeneric{
    @SerializedName("betID")
    @Expose
    private String betID;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("winState")
    @Expose
    private String winStatee;
    @SerializedName("totalOdd")
    @Expose
    private int totalOdd;

    public String getBetID() {
        return betID;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getActive() {
        return active;
    }

    public String getWinState() {
        return winStatee;
    }

    public int getTotalOdd() {
        return totalOdd;
    }

    @Override
    public HashMap<String, String> GetNesto() {
        return null;
    }

    @Override
    public String getId() {
        return betID;
    }

    @Override
    public int totalOdd() {
        return  totalOdd;
    }

    @Override
    public String winState() {
        return winStatee;
    }
}
