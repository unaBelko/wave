package com.example.armin.wave.utakmice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.armin.wave.R;
import com.example.armin.wave.Storage.SharedPreferencesHelper;
import com.example.armin.wave.adapter.ScheduleAdapter;
import com.example.armin.wave.matchBet.MatchBetActivity;
import com.example.armin.wave.utakmice.model.GenericList;
import com.example.armin.wave.utakmice.model.Match;
import com.example.armin.wave.utakmice.model.Match_;
import com.example.armin.wave.utakmice.model.UtakmiceResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Una on 28.10.2017..
 */

public class UtakmiceActivity extends AppCompatActivity implements IUtakmiceContract.View {
    @BindView(R.id.rv_schedule)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshMatches)
    SwipeRefreshLayout mRefresh;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    IUtakmiceContract.Presenter mPresenter;
    List<GenericList> lista;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utakmice_activity);
        ButterKnife.bind(this);
        mPresenter = new UtakmicePresenter(this, this);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter.LoadMatches();

        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
                mPresenter.LoadMatches();
            }
        });
        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    @Override
    public void OnMatchesLoad(String json) {
        if(mAdapter != null){
            ((ScheduleAdapter)mAdapter).Clear();
        }
        Gson gson = new Gson();
        UtakmiceResponse utakmiceResponse = gson.fromJson(json, UtakmiceResponse.class);
        lista = new ArrayList<>();

        for (Match match : utakmiceResponse.getMatches()) {
            lista.add(match);
            for (Match_ m : match.getMatches()) {
                lista.add(m);
            }
        }

        mAdapter = new ScheduleAdapter(lista, this, this, true);
        mRecyclerView.setAdapter(mAdapter);
        mRefresh.setRefreshing(false);

    }

    @Override
    public void OnClick(int position) {
        Intent intent = new Intent(this, MatchBetActivity.class);
        intent.putExtra("match_id",lista.get(position).GetMatchIDDirty());
        intent.putExtra("home_team",lista.get(position).GetName().get("home_team"));
        intent.putExtra("away_team",lista.get(position).GetName().get("away_team"));
        intent.putExtra("home_odds",lista.get(position).GetName().get("home_win"));
        intent.putExtra("draw_odds",lista.get(position).GetName().get("draw"));
        intent.putExtra("away_odds",lista.get(position).GetName().get("away_win"));
        intent.putExtra("time",lista.get(position).GetName().get("time"));

        startActivity(intent);

    }


}
