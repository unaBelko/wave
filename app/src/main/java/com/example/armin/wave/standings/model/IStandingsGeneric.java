package com.example.armin.wave.standings.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public interface IStandingsGeneric {
    List<TeamStandings> GetSomething();

    String GetTeamName();

    String GetPoints();

    String GetGet();
    String GetRank();
}
