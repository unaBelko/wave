package com.example.armin.wave.standings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.network.UtakmiceService;
import com.example.armin.wave.standings.model.StandingsResponse;
import com.example.armin.wave.standings.model.TeamStandings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Una on 28.10.2017..
 */

public class StandingsPresenter implements IStandingsContract.Presenter {

    UtakmiceService service;
    IStandingsContract.View mView;
    Context mContext;

    public StandingsPresenter(IStandingsContract.View view, Context context){
        service = new UtakmiceService();
        mView = view;
        mContext = context;
    }
    public void LoadData(String tournamentId) {
        SharedPreferences sharedPreferences = SharedPreferencesHelper.GetSharedPreferences(mContext, "session");
        String sessionID = sharedPreferences.getString("sessionID", "null");

        service.GetUtakmiceApi().GetStandings("PHPSESSID="+sessionID,tournamentId).enqueue(new Callback<List<StandingsResponse>>() {
            @Override
            public void onResponse(Call<List<StandingsResponse>> call, Response<List<StandingsResponse>> response) {
                List<StandingsResponse> standingsResponse = response.body();
                List<TeamStandings> teamStandings = standingsResponse.get(0).getTeams();

                mView.OnLoadData(standingsResponse);
            }

            @Override
            public void onFailure(Call<List<StandingsResponse>> call, Throwable t) {
                Log.i("failure",t.getMessage());
            }
        });
    }
}
