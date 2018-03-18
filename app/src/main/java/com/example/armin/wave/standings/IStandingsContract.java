package com.example.armin.wave.standings;

import com.example.armin.wave.standings.model.StandingsResponse;

import java.util.List;

/**
 * Created by Una on 28.10.2017..
 */

public interface IStandingsContract {

    interface Presenter{
        void LoadData(String tournamentId);
    }

    interface View{
        void OnLoadData(List<StandingsResponse> standingsResponse);
    }
}
