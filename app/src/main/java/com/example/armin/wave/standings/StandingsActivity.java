package com.example.armin.wave.standings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.armin.wave.R;
import com.example.armin.wave.adapter.StandingsAdapter;
import com.example.armin.wave.standings.model.IStandingsGeneric;
import com.example.armin.wave.standings.model.StandingsResponse;
import com.example.armin.wave.standings.model.TeamStandings;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 28.10.2017..
 */

public class StandingsActivity extends AppCompatActivity implements IStandingsContract.View{
    IStandingsContract.Presenter mPresenter;
    @BindView(R.id.rv_standings)
    RecyclerView mStandings;
    RecyclerView.LayoutManager mLayoutManager;
    StandingsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standings_activity);
        ButterKnife.bind(this);
        mPresenter = new StandingsPresenter(this,this);

        Intent intent = getIntent();
        String tournamentId = intent.getStringExtra("tournament_id");
        mStandings.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mStandings.setLayoutManager(mLayoutManager);
        mPresenter.LoadData(tournamentId);
    }

    @Override
    public void OnLoadData(List<StandingsResponse> standingsResponse) {
        List<IStandingsGeneric> generics = new ArrayList<>();
        for (StandingsResponse response : standingsResponse){
            generics.add(response);
            for (TeamStandings temp : response.getTeams()){
                generics.add(temp);
            }
        }
        mAdapter = new StandingsAdapter(generics,this,this,standingsResponse);
        mStandings.setAdapter(mAdapter);
    }
}
