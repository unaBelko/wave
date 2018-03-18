package com.example.armin.wave.ticket.model;

import java.util.HashMap;

/**
 * Created by Una on 29.10.2017..
 */

public interface ITicketGeneric {
    HashMap<String, String> GetNesto();
    String getId();
    int totalOdd();
    String winState();
}
